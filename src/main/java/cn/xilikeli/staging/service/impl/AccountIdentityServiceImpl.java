package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.common.constant.IdentityTypeConstant;
import cn.xilikeli.staging.common.constant.business.AccountCodeConstant;
import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.util.Assert;
import cn.xilikeli.staging.common.util.EncryptUtil;
import cn.xilikeli.staging.model.AccountIdentityDO;
import cn.xilikeli.staging.repository.AccountIdentityRepository;
import cn.xilikeli.staging.service.AccountIdentityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        Assert.checkArgument(
                Objects.isNull(accountIdentityDO),
                CodeEnum.PARAMETER_NOT_NULL.getCode()
        );

        Assert.checkArgument(
                Objects.isNull(accountIdentityDO.getAccountId()),
                CodeEnum.PARAMETER_NOT_NULL.getCode()
        );

        Assert.checkArgument(
                StringUtils.isBlank(accountIdentityDO.getIdentityType()),
                CodeEnum.PARAMETER_NOT_NULL.getCode()
        );

        Assert.checkArgument(
                StringUtils.isBlank(accountIdentityDO.getIdentifier()),
                CodeEnum.PARAMETER_NOT_NULL.getCode()
        );

        this.accountIdentityRepository.save(accountIdentityDO);
    }

    @Override
    public void createUsernamePasswordIdentity(Long accountId, String username, String password) {
        Assert.checkArgument(
                StringUtils.isBlank(password),
                CodeEnum.PARAMETER_NOT_NULL.getCode()
        );

        // 密码加密
        password = EncryptUtil.encrypt(password);
        this.createIdentity(accountId, IdentityTypeConstant.USERNAME_PASSWORD_IDENTITY, username, password);
    }

    @Override
    public void createWeChatAppletsIdentity(Long accountId, String openid) {
        AccountIdentityDO accountIdentityDO = AccountIdentityDO.builder()
                .accountId(accountId)
                .identityType(IdentityTypeConstant.WE_CHAT_APPLETS_OPENID_IDENTITY)
                .identifier(openid)
                .build();

        this.createIdentity(accountIdentityDO);
    }

    @Override
    public boolean verifyUsernamePassword(Long accountId, String username, String password) {
        Optional<AccountIdentityDO> accountIdentityOptional = this.accountIdentityRepository.findOne(
                Example.of(
                        AccountIdentityDO.builder()
                                .accountId(accountId)
                                .identityType(IdentityTypeConstant.USERNAME_PASSWORD_IDENTITY)
                                .identifier(username)
                                .build()
                )
        );

        AccountIdentityDO accountIdentityDO = accountIdentityOptional.orElseThrow(
                () -> new NotFoundException(AccountCodeConstant.NOT_FOUND_ACCOUNT_IDENTITY)
        );

        return EncryptUtil.verify(accountIdentityDO.getCredential(), password);
    }

    @Override
    public Optional<AccountIdentityDO> getIdentityByIdentifier(String identifier) {
        return this.accountIdentityRepository.findByIdentifier(identifier);
    }

    @Override
    public AccountIdentityDO getIdentityByAccountIdAndIdentityType(Long accountId, String identityType) {
        Optional<AccountIdentityDO> accountIdentityOptional = this.accountIdentityRepository.findByAccountIdAndIdentityType(accountId, identityType);
        return accountIdentityOptional.orElseThrow(
                () -> new NotFoundException(AccountCodeConstant.NOT_FOUND_ACCOUNT_IDENTITY)
        );
    }

}