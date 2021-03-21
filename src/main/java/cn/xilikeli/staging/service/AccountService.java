package cn.xilikeli.staging.service;

import cn.xilikeli.staging.dto.account.AccountDTO;
import cn.xilikeli.staging.dto.account.LoginDTO;
import cn.xilikeli.staging.dto.account.RegisterDTO;
import cn.xilikeli.staging.vo.account.LoginResultVO;
import cn.xilikeli.staging.vo.account.RegisterResultVO;

/**
 * <p>
 * 用户基本信息服务类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public interface AccountService {

    /**
     * 根据邮箱检查用户是否存在
     *
     * @param email 邮箱
     * @return 存在返回 true, 不存在返回 false
     */
    boolean checkAccountExistByEmail(String email);

    /**
     * 根据手机号检查用户是否存在
     *
     * @param mobile 手机号
     * @return 存在返回 true, 不存在返回 false
     */
    boolean checkAccountExistByMobile(String mobile);

    /**
     * 根据用户 ID 和登录类型获取用户信息
     *
     * @param accountId    用户 ID
     * @param identityType 登录类型(手机号, 邮箱, 用户名或第三方应用名称(微信, 微博, QQ 等)
     * @return 返回相应的用户信息
     */
    AccountDTO getAccountInfoByIdAndIdentityType(Long accountId, String identityType);

    /**
     * 通过用户名和密码的方式进行注册
     *
     * @param registerDTO 用户注册信息 DTO
     * @return 注册成功返回成功的响应结果, 否则返回失败的响应结果
     */
    RegisterResultVO registerByUsernameAndPassword(RegisterDTO registerDTO);

    /**
     * 通过微信小程序的方式进行注册
     *
     * @param registerDTO 用户注册信息 DTO
     * @return 注册成功返回成功的响应结果, 否则返回失败的响应结果
     */
    RegisterResultVO registerByWeChatApplets(RegisterDTO registerDTO);

    /**
     * 通过用户名和密码的方式进行登录
     *
     * @param loginDTO 用户登录信息 DTO
     * @return 登录成功返回成功的响应结果, 否则返回失败的响应结果
     */
    LoginResultVO loginByUsernameAndPassword(LoginDTO loginDTO);

    /**
     * 通过微信小程序的方式进行登录
     *
     * @param loginDTO 用户登录信息 DTO
     * @return 登录成功返回成功的响应结果, 否则返回失败的响应结果
     */
    LoginResultVO loginByWeChatApplets(LoginDTO loginDTO);

}