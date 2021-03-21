package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.LocalUser;
import cn.xilikeli.staging.common.constant.business.AccountCodeConstant;
import cn.xilikeli.staging.common.enumeration.LoginTypeEnum;
import cn.xilikeli.staging.common.enumeration.RegisterTypeEnum;
import cn.xilikeli.staging.common.exception.http.ParameterException;
import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.common.util.JwtTokenUtil;
import cn.xilikeli.staging.dto.account.AccountDTO;
import cn.xilikeli.staging.dto.account.LoginDTO;
import cn.xilikeli.staging.dto.account.RegisterDTO;
import cn.xilikeli.staging.dto.account.TokenDTO;
import cn.xilikeli.staging.service.AccountService;
import cn.xilikeli.staging.vo.account.LoginResultVO;
import cn.xilikeli.staging.vo.account.RegisterResultVO;
import cn.xilikeli.staging.vo.response.CreatedResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户 API 接口控制器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
@Validated
@RestController
@RequestMapping("/v1/account")
@Api(value = "用户 API 接口", tags = {"用户业务的相关接口"})
public class AccountController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 用户注册
     *
     * @param registerDTO 用户注册信息 DTO
     * @return 注册成功返回成功的响应结果, 否则返回失败的响应结果
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册接口", notes = "注册一个用户", httpMethod = "POST")
    public CreatedResponseVO<RegisterResultVO> register(
            @Validated
            @RequestBody RegisterDTO registerDTO) {
        RegisterResultVO registerResultVO;

        switch (RegisterTypeEnum.toEnum(registerDTO.getRegisterType())) {
            case USERNAME_PASSWORD_REGISTER:
                registerResultVO = this.accountService.registerByUsernameAndPassword(registerDTO);
                break;
            case WE_CHAT_APPLETS_REGISTER:
                registerResultVO = this.accountService.registerByWeChatApplets(registerDTO);
                break;
            default:
                throw new ParameterException(AccountCodeConstant.INVALID_REGISTER_TYPE);
        }

        return new CreatedResponseVO<>(AccountCodeConstant.REGISTER_SUCCESS, registerResultVO);
    }

    /**
     * 用户登录
     *
     * @param loginDTO 用户登录信息 DTO
     * @return 登录成功返回成功的响应结果, 否则返回失败的响应结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录", httpMethod = "POST")
    public LoginResultVO login(
            @Validated
            @RequestBody LoginDTO loginDTO) {
        LoginResultVO loginResultVO = new LoginResultVO();

        switch (LoginTypeEnum.toEnum(loginDTO.getLoginType())) {
            case USERNAME_PASSWORD_LOGIN:
                loginResultVO = this.accountService.loginByUsernameAndPassword(loginDTO);
                break;
            case WE_CHAT_APPLETS_LOGIN:
                loginResultVO = this.accountService.loginByWeChatApplets(loginDTO);
                break;
            default:
                throw new ParameterException(AccountCodeConstant.INVALID_LOGIN_TYPE);
        }

        return loginResultVO;
    }

    /**
     * 校验 token 令牌信息是否合法
     *
     * @param tokenDTO token 令牌信息 DTO
     * @return 返回相应的校验结果
     */
    @PostMapping("/token/verify")
    @ApiOperation(value = "校验 token 令牌信息接口", notes = "校验 token 令牌信息是否合法", httpMethod = "POST")
    public Map<String, Boolean> verify(@RequestBody TokenDTO tokenDTO) {
        Map<String, Boolean> map = new HashMap<>(16);
        Boolean valid = JwtTokenUtil.verifyToken(tokenDTO.getToken());
        map.put("is_valid", valid);
        return map;
    }

    /**
     * 获取当前登录用户的信息
     *
     * @return 返回当前登录用户的信息
     */
    @ScopeLevel
    @GetMapping("/info")
    @ApiOperation(value = "获取当前登录用户信息接口", notes = "获取当前登录用户的信息", httpMethod = "GET")
    public AccountDTO getCurrentLoginAccountInfo() {
        return LocalUser.getLocalUser();
    }

}