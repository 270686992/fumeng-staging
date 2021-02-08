package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.common.constant.BusinessCodeConstant;
import cn.xilikeli.staging.common.constant.IdentityConstant;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.exception.http.ParameterException;
import cn.xilikeli.staging.common.util.Assert;
import cn.xilikeli.staging.common.util.JwtTokenUtil;
import cn.xilikeli.staging.dto.account.AccountDTO;
import cn.xilikeli.staging.dto.account.LoginDTO;
import cn.xilikeli.staging.dto.account.RegisterDTO;
import cn.xilikeli.staging.model.AccountDO;
import cn.xilikeli.staging.model.AccountIdentityDO;
import cn.xilikeli.staging.repository.AccountRepository;
import cn.xilikeli.staging.service.AccountIdentityService;
import cn.xilikeli.staging.service.AccountService;
import cn.xilikeli.staging.vo.account.LoginResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * 用户基本信息服务实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private AccountIdentityService accountIdentityService;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setAccountIdentityService(AccountIdentityService accountIdentityService) {
        this.accountIdentityService = accountIdentityService;
    }

    @Override
    public boolean checkAccountExistByEmail(String mobile) {
        int count = this.accountRepository.countByEmail(mobile);
        return count > 0;
    }

    @Override
    public boolean checkAccountExistByMobile(String mobile) {
        int count = this.accountRepository.countByMobile(mobile);
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<Long> createAccount(RegisterDTO registerDTO) {
        boolean exist = this.accountIdentityService.checkAccountExistByIdentifier(registerDTO.getUsername());
        Assert.checkArgument(exist, BusinessCodeConstant.USERNAME_REPEAT);

        exist = this.checkAccountExistByEmail(registerDTO.getEmail());
        Assert.checkArgument(exist, BusinessCodeConstant.EMAIL_REPEAT);

        exist = this.checkAccountExistByMobile(registerDTO.getMobile());
        Assert.checkArgument(exist, BusinessCodeConstant.MOBILE_REPEAT);

        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(registerDTO, accountDO);
        accountDO = this.accountRepository.save(accountDO);

        this.accountIdentityService.createUsernamePasswordIdentity(
                accountDO.getId(),
                registerDTO.getUsername(),
                registerDTO.getPassword()
        );

        return Optional.ofNullable(accountDO.getId());
    }

    @Override
    public AccountDTO getAccountInfoById(Long accountId) {
        AccountDO accountDO = this.accountRepository.findById(accountId).orElseThrow(
                () -> new NotFoundException(BusinessCodeConstant.NOT_FOUND_ACCOUNT)
        );

        AccountIdentityDO accountIdentityDO = this.accountIdentityService.getIdentityByAccountIdAndIdentityType(
                accountDO.getId(), IdentityConstant.USERNAME_PASSWORD_IDENTITY
        );

        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(accountDO, accountDTO);
        accountDTO.setUsername(accountIdentityDO.getIdentifier());
        return accountDTO;
    }

    @Override
    public LoginResultVO loginByUsernameAndPassword(LoginDTO loginDTO) {
        AccountIdentityDO accountIdentityDO = this.accountIdentityService.getIdentityByIdentifier(
                loginDTO.getUsername()
        );

        boolean valid = this.accountIdentityService.verifyUsernamePassword(
                accountIdentityDO.getAccountId(),
                accountIdentityDO.getIdentifier(),
                loginDTO.getPassword()
        );

        if (!valid) {
            throw new ParameterException(BusinessCodeConstant.USERNAME_OR_PASSWORD_ERROR);
        }

        AccountDTO accountDTO = this.getAccountInfoById(accountIdentityDO.getAccountId());
        String token = JwtTokenUtil.makeToken(accountDTO.getId());
        LoginResultVO loginResultVO = new LoginResultVO();
        BeanUtils.copyProperties(accountDTO, loginResultVO);
        loginResultVO.setToken(token);
        return loginResultVO;
    }

}