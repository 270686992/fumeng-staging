package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.constant.BusinessCodeConstant;
import cn.xilikeli.staging.common.enumeration.LoginTypeEnum;
import cn.xilikeli.staging.common.exception.http.FailedException;
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
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
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
    public CreatedResponseVO<RegisterResultVO> register(@RequestBody
                                                        @Validated RegisterDTO registerDTO) {
        Long accountId = this.accountService
                .createAccount(registerDTO)
                .orElseThrow(
                        () -> new FailedException(BusinessCodeConstant.REGISTER_FAILED)
                );

        String token = JwtTokenUtil.makeToken(accountId);

        RegisterResultVO registerResultVO = RegisterResultVO.builder()
                .accountId(accountId)
                .token(token)
                .build();

        return new CreatedResponseVO<>(BusinessCodeConstant.REGISTER_SUCCESS, registerResultVO);
    }

    /**
     * 用户登录
     *
     * @param loginDTO 用户登录信息 DTO
     * @return 登录成功返回成功的响应结果, 否则返回失败的响应结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录", httpMethod = "POST")
    public LoginResultVO login(@RequestBody
                               @Validated LoginDTO loginDTO) {
        LoginResultVO loginResultVO = new LoginResultVO();

        switch (LoginTypeEnum.toEnum(loginDTO.getLoginType())) {
            case USERNAME_PASSWORD_IDENTITY:
                loginResultVO = this.accountService.loginByUsernameAndPassword(loginDTO);
                break;
            case WX_IDENTITY:
                // TODO 微信登录, 待扩展
                break;
            case EMAIL_IDENTITY:
                // TODO 邮箱登录, 待扩展
                break;
            case MOBILE_IDENTITY:
                // TODO 手机号登录, 待扩展
                break;
            default:
                throw new ParameterException(BusinessCodeConstant.INVALID_LOGIN_TYPE);
        }

        return loginResultVO;
    }

    /**
     * 校验前端传来的 token 令牌信息是否合法
     *
     * @param tokenDTO token 令牌信息 DTO
     * @return 返回相应的校验结果
     */
    @PostMapping("/token/verify")
    @ApiOperation(value = "校验 token 令牌信息接口", notes = "校验前端传来的 token 令牌信息是否合法", httpMethod = "POST")
    public Map<String, Boolean> verify(@RequestBody TokenDTO tokenDTO) {
        Map<String, Boolean> map = new HashMap<>(16);
        Boolean valid = JwtTokenUtil.verifyToken(tokenDTO.getToken());
        map.put("is_valid", valid);
        return map;
    }

    /**
     * 根据用户 ID 获取相应用户的信息
     *
     * @param accountId 用户 ID
     * @return 返回获取的用户的信息
     */
    @ScopeLevel
    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户信息接口", notes = "根据用户 ID 获取相应用户的信息", httpMethod = "GET")
    public AccountDTO getBookById(@PathVariable(name = "id")
                                  @Positive(message = "{id.positive}")
                                  @ApiParam(name = "id", value = "用户 ID", required = true, example = "1") Long accountId) {
        return this.accountService.getAccountInfoById(accountId);
    }


}