package cn.xilikeli.staging.common.interceptor;

import cn.xilikeli.staging.common.LocalUser;
import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.exception.http.AuthenticationException;
import cn.xilikeli.staging.common.exception.http.AuthorizationException;
import cn.xilikeli.staging.common.exception.http.ForbiddenException;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.util.JwtTokenUtil;
import cn.xilikeli.staging.dto.account.AccountDTO;
import cn.xilikeli.staging.service.AccountService;
import com.auth0.jwt.interfaces.Claim;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 权限拦截器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/6
 * @since JDK1.8
 */
@SuppressWarnings("all")
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求进入 Controller 之前的处理, 放行返回 true, 不放行返回 false
        // 获取当前要访问的 API 上的 ScopeLevel 注解的信息
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()) {
            // 公开 API, 返回 true
            return true;
        }

        // 获取请求中的 token 令牌(传递 token 的键: Authorization)
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(bearerToken)) {
            throw new AuthenticationException(CodeEnum.UN_AUTHENTICATION.getCode());
        }

        // 判断发送过来的令牌是否开头带有 Bearer, JWT 的一个标准
        if (!bearerToken.startsWith("Bearer")) {
            throw new AuthenticationException(CodeEnum.UN_AUTHENTICATION.getCode());
        }

        // 避免数组越界
        String[] tokens = bearerToken.split(" ");
        if (tokens.length != 2) {
            throw new AuthenticationException(CodeEnum.UN_AUTHENTICATION.getCode());
        }

        // 提取 token 令牌
        String token = tokens[1];

        // 校验 token 解析 token 令牌信息
        Optional<Map<String, Claim>> tokenInfoOptional = JwtTokenUtil.verifyTokenAndGetClaims(token);
        Map<String, Claim> tokenInfo = tokenInfoOptional.orElseThrow(
                () -> new AuthenticationException(CodeEnum.UN_AUTHENTICATION.getCode())
        );

        // 令牌合法, 比对 ScopeLevel
        boolean valid = this.hasPermission(scopeLevel.get(), tokenInfo);
        if (valid) {
            this.setToThreadLocal(tokenInfo);
        }

        return valid;
    }

    /**
     * 判断令牌中携带的用户信息是否具有访问 API 的权限
     *
     * @param scopeLevel 用户分组级别
     * @param tokenInfo  令牌携带的信息
     * @return 具有访问权限返回 true, 没有访问权限抛出一个禁止操作异常
     */
    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> tokenInfo) {
        // 获取当前要访问的 API 上的用户分组级别的值
        int apiScopelevel = scopeLevel.value();
        // 获取令牌中携带的用户级别
        Integer accountScopeLevel = tokenInfo.get(JwtTokenUtil.SCOPE_LEVEL_KEY).asInt();
        // 进行级别比对判断
        if (apiScopelevel > accountScopeLevel) {
            // 用户级别低于 API 上的级别, 没有权限
            throw new ForbiddenException(CodeEnum.UN_AUTHORIZATION.getCode());
        }
        // 用户级别大于等于 API 上的级别, 拥有权限, 返回 true
        return true;
    }

    /**
     * 设置当前登录用户信息
     *
     * @param tokenInfo 令牌携带的信息
     */
    private void setToThreadLocal(Map<String, Claim> tokenInfo) {
        Long accountId = tokenInfo.get(JwtTokenUtil.ACCOUNT_ID_KEY).asLong();
        Integer scopeLevel = tokenInfo.get(JwtTokenUtil.SCOPE_LEVEL_KEY).asInt();

        AccountDTO accountDTO = null;
        try {
            accountDTO = this.accountService.getAccountInfoById(accountId);
        } catch (NotFoundException e) {
            throw new AuthorizationException(CodeEnum.UN_AUTHORIZATION.getCode());
        }

        LocalUser.setLocalUser(accountDTO, scopeLevel);
    }

    /**
     * 从要访问的 API 的方法上读取 ScopeLevel 的信息
     *
     * @param handler 要访问的 API 的方法
     * @return 返回要访问的 API 的方法上的 ScopeLevel 的信息
     */
    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取 ScopeLevel 注解
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (scopeLevel == null) {
                return Optional.empty();
            }
            // 确定 scopeLevel 必须要有值的话, 使用 of 方法, 如果没有值会在这里报错, 容易早发现
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理当前登录用户信息
        LocalUser.clearLocalUser();
        super.afterCompletion(request, response, handler, ex);
    }

}