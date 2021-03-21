package cn.xilikeli.staging.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xilikeli.staging.common.constant.IdentityTypeConstant;
import cn.xilikeli.staging.common.constant.business.AccountCodeConstant;
import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.exception.http.FailedException;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.exception.http.ParameterException;
import cn.xilikeli.staging.common.exception.http.ServerErrorException;
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
import cn.xilikeli.staging.vo.account.RegisterResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${we-chat.code2session}")
    private String code2SessionUrl;

    @Value("${we-chat.appid}")
    private String appId;

    @Value("${we-chat.appsecret}")
    private String appSecret;

    private ObjectMapper objectMapper;

    private AccountRepository accountRepository;

    private AccountIdentityService accountIdentityService;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
    public AccountDTO getAccountInfoByIdAndIdentityType(Long accountId, String identityType) {
        AccountDO accountDO = this.accountRepository.findById(accountId).orElseThrow(
                () -> new NotFoundException(AccountCodeConstant.NOT_FOUND_ACCOUNT)
        );

        AccountIdentityDO accountIdentityDO = this.accountIdentityService.getIdentityByAccountIdAndIdentityType(
                accountDO.getId(), identityType
        );

        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(accountDO, accountDTO);
        accountDTO.setAccount(accountIdentityDO.getIdentifier());
        return accountDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterResultVO registerByUsernameAndPassword(RegisterDTO registerDTO) {
        Assert.checkArgument(
                StringUtils.isBlank(registerDTO.getPassword()),
                AccountCodeConstant.PASSWORD_NOT_BLANK
        );

        Assert.checkArgument(
                StringUtils.isBlank(registerDTO.getConfirmPassword()),
                AccountCodeConstant.CONFIRM_PASSWORD_NOT_BLANK
        );

        Assert.checkArgument(
                !StringUtils.equals(registerDTO.getPassword(), registerDTO.getConfirmPassword()),
                AccountCodeConstant.PASSWORD_INCONSISTENT
        );

        boolean exist = this.accountIdentityService.checkAccountExistByIdentifier(registerDTO.getAccount());
        Assert.checkArgument(exist, AccountCodeConstant.ACCOUNT_REPEAT);

        AccountDO accountDO = this.accountRepository.save(
                AccountDO.builder()
                        .nickname(registerDTO.getAccount())
                        .build()
        );

        this.accountIdentityService.createUsernamePasswordIdentity(
                accountDO.getId(),
                registerDTO.getAccount(),
                registerDTO.getPassword()
        );

        String token = JwtTokenUtil.makeToken(accountDO.getId(), IdentityTypeConstant.USERNAME_PASSWORD_IDENTITY);

        return RegisterResultVO.builder()
                .accountId(accountDO.getId())
                .token(token)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterResultVO registerByWeChatApplets(RegisterDTO registerDTO) {
        Map<String, Object> weChatIdentitySession = this.getWeChatIdentitySession(
                registerDTO.getAccount(),
                AccountCodeConstant.REGISTER_FAILED,
                "进行微信小程序用户注册时反序列化微信服务器返回的结果出错: "
        );

        // 获取微信小程序 openid
        String openid = (String) weChatIdentitySession.get("openid");
        Assert.checkFailed(StringUtils.isBlank(openid), AccountCodeConstant.REGISTER_FAILED);

        this.accountIdentityService.getIdentityByIdentifier(openid).ifPresent(accountIdentityDO -> {
            throw new FailedException(AccountCodeConstant.WE_CHAT_APPLETS_OPENID_REPEAT);
        });

        AccountDO accountDO = this.accountRepository.save(new AccountDO());
        this.accountIdentityService.createWeChatAppletsIdentity(accountDO.getId(), openid);

        String token = JwtTokenUtil.makeToken(accountDO.getId(), IdentityTypeConstant.WE_CHAT_APPLETS_OPENID_IDENTITY);

        return RegisterResultVO.builder()
                .accountId(accountDO.getId())
                .token(token)
                .build();
    }

    @Override
    public LoginResultVO loginByUsernameAndPassword(LoginDTO loginDTO) {
        Assert.checkArgument(
                StringUtils.isBlank(loginDTO.getPassword()),
                AccountCodeConstant.PASSWORD_NOT_BLANK
        );

        Optional<AccountIdentityDO> accountIdentityOptional = this.accountIdentityService
                .getIdentityByIdentifier(loginDTO.getAccount());

        AccountIdentityDO accountIdentityDO = accountIdentityOptional.orElseThrow(
                () -> new NotFoundException(AccountCodeConstant.NOT_FOUND_ACCOUNT_IDENTITY)
        );

        boolean valid = this.accountIdentityService.verifyUsernamePassword(
                accountIdentityDO.getAccountId(),
                accountIdentityDO.getIdentifier(),
                loginDTO.getPassword()
        );

        if (!valid) {
            throw new ParameterException(AccountCodeConstant.USERNAME_OR_PASSWORD_ERROR);
        }

        AccountDTO accountDTO = this.getAccountInfoByIdAndIdentityType(accountIdentityDO.getAccountId(), IdentityTypeConstant.USERNAME_PASSWORD_IDENTITY);
        String token = JwtTokenUtil.makeToken(accountDTO.getId(), IdentityTypeConstant.USERNAME_PASSWORD_IDENTITY);
        LoginResultVO loginResultVO = BeanUtil.copyProperties(accountDTO, LoginResultVO.class);
        loginResultVO.setToken(token);
        return loginResultVO;
    }

    @Override
    public LoginResultVO loginByWeChatApplets(LoginDTO loginDTO) {
        Map<String, Object> weChatIdentitySession = this.getWeChatIdentitySession(
                loginDTO.getAccount(),
                AccountCodeConstant.LOGIN_FAILED,
                "进行微信小程序用户登录时反序列化微信服务器返回的结果出错: "
        );

        // 获取微信小程序 openid
        String openid = (String) weChatIdentitySession.get("openid");
        Assert.checkFailed(StringUtils.isBlank(openid), AccountCodeConstant.LOGIN_FAILED);

        Optional<AccountIdentityDO> accountIdentityOptional = this.accountIdentityService
                .getIdentityByIdentifier(openid);

        Assert.checkFailed(!accountIdentityOptional.isPresent(), AccountCodeConstant.NOT_FOUND_ACCOUNT_IDENTITY);

        AccountDTO accountDTO = this.getAccountInfoByIdAndIdentityType(
                accountIdentityOptional.get().getAccountId(),
                IdentityTypeConstant.WE_CHAT_APPLETS_OPENID_IDENTITY
        );

        String token = JwtTokenUtil.makeToken(accountDTO.getId(), IdentityTypeConstant.WE_CHAT_APPLETS_OPENID_IDENTITY);
        LoginResultVO loginResultVO = BeanUtil.copyProperties(accountDTO, LoginResultVO.class);
        loginResultVO.setToken(token);
        return loginResultVO;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getWeChatIdentitySession(String weChatCode, Integer code, String logMessage) {
        // 拼接请求 url, 需要三个参数: code 码, 小程序 appId, 小程序 appSecret
        String url = MessageFormat.format(code2SessionUrl, this.appId, this.appSecret, weChatCode);

        // 发送 HTTP 请求给微信服务器校验用户身份信息
        RestTemplate rest = new RestTemplate();
        String sessionText = rest.getForObject(url, String.class);

        // 对得到的返回结果进行反序列化
        Map<String, Object> session;
        try {
            Assert.checkFailed(Objects.isNull(sessionText), code);
            session = objectMapper.readValue(sessionText, HashMap.class);
            Assert.checkFailed(Objects.isNull(session), code);
            // 判断微信服务器是否有返回错误码
            Integer weChatErrorCode = (Integer) session.get("errcode");
            Assert.checkFailed(Objects.nonNull(weChatErrorCode), code);
        } catch (JsonProcessingException e) {
            log.error(logMessage, e);
            throw new ServerErrorException(CodeEnum.INTERNAL_SERVER_ERROR.getCode());
        }

        return session;
    }

}