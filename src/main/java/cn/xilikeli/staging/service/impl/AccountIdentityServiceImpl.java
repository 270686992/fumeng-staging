package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.common.constant.BusinessCodeConstant;
import cn.xilikeli.staging.common.constant.IdentityConstant;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.util.EncryptUtil;
import cn.xilikeli.staging.model.AccountIdentityDO;
import cn.xilikeli.staging.repository.AccountIdentityRepository;
import cn.xilikeli.staging.service.AccountIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户认证信息服务实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
@Service
public class AccountIdentityServiceImpl implements AccountIdentityService {

    private AccountIdentityRepository accountIdentityRepository;

    @Autowired
    public void setAccountIdentityRepository(AccountIdentityRepository accountIdentityRepository) {
        this.accountIdentityRepository = accountIdentityRepository;
    }

    @Override
    public boolean checkAccountExistByIdentifier(String identifier) {
        int count = this.accountIdentityRepository.countByIdentifier(identifier);
        return count > 0;
    }

    @Override
    public void createIdentity(Long accountId, String identityType, String identifier, String credential) {
        AccountIdentityDO accountIdentityDO = new AccountIdentityDO();
        accountIdentityDO.setAccountId(accountId)
                .setIdentityType(identityType)
                .setIdentifier(identifier)
                .setCredential(credential);
        this.createIdentity(accountIdentityDO);
    }

    @Override
    public void createIdentity(AccountIdentityDO accountIdentityDO) {
        this.accountIdentityRepository.save(accountIdentityDO);
    }

    @Override
    public void createUsernamePasswordIdentity(Long accountId, String username, String password) {
        // 密码加密
        password = EncryptUtil.encrypt(password);
        this.createIdentity(accountId, IdentityConstant.USERNAME_PASSWORD_IDENTITY, username, password);
    }

    @Override
    public boolean verifyUsernamePassword(Long accountId, String username, String password) {
        Optional<AccountIdentityDO> accountIdentityOptional = this.accountIdentityRepository.findOne(
                Example.of(
                        AccountIdentityDO.builder()
                                .accountId(accountId)
                                .identityType(IdentityConstant.USERNAME_PASSWORD_IDENTITY)
                                .identifier(username)
                                .build()
                )
        );

        AccountIdentityDO accountIdentityDO = accountIdentityOptional.orElseThrow(
                () -> new NotFoundException(BusinessCodeConstant.NOT_FOUND_ACCOUNT_IDENTITY)
        );

        return EncryptUtil.verify(accountIdentityDO.getCredential(), password);
    }

    @Override
    public AccountIdentityDO getIdentityByIdentifier(String identifier) {
        Optional<AccountIdentityDO> accountIdentityOptional = this.accountIdentityRepository.findByIdentifier(identifier);
        return accountIdentityOptional.orElseThrow(
                () -> new NotFoundException(BusinessCodeConstant.NOT_FOUND_ACCOUNT_IDENTITY)
        );
    }

    @Override
    public AccountIdentityDO getIdentityByAccountIdAndIdentityType(Long accountId, String identityType) {
        Optional<AccountIdentityDO> accountIdentityOptional = this.accountIdentityRepository.findByAccountIdAndIdentityType(accountId, identityType);
        return accountIdentityOptional.orElseThrow(
                () -> new NotFoundException(BusinessCodeConstant.NOT_FOUND_ACCOUNT_IDENTITY)
        );
    }

}