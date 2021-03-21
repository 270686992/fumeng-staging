package cn.xilikeli.staging.common.constant.business;

/**
 * <p>
 * 账号业务消息码常量
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/3/21
 * @since JDK1.8
 */
public interface AccountCodeConstant {

    /**
     * 注册成功消息码
     */
    Integer REGISTER_SUCCESS = 1100;

    /**
     * 登录成功消息码
     */
    Integer LOGIN_SUCCESS = 1101;

    /**
     * 更新用户信息成功消息码
     */
    Integer UPDATE_ACCOUNT_SUCCESS = 1102;

    /**
     * 密码修改成功消息码
     */
    Integer UPDATE_PASSWORD_SUCCESS = 1103;

    /**
     * 未找到相关用户消息码
     */
    Integer NOT_FOUND_ACCOUNT = 30000;

    /**
     * 未找到相关用户认证信息消息码
     */
    Integer NOT_FOUND_ACCOUNT_IDENTITY = 30010;

    /**
     * 账号重复消息码
     */
    Integer ACCOUNT_REPEAT = 30020;

    /**
     * 邮箱重复消息码
     */
    Integer EMAIL_REPEAT = 30030;

    /**
     * 手机号重复消息码
     */
    Integer MOBILE_REPEAT = 30040;

    /**
     * 微信小程序 openid 重复消息码
     */
    Integer WE_CHAT_APPLETS_OPENID_REPEAT = 30050;

    /**
     * 无效注册类型消息码
     */
    Integer INVALID_REGISTER_TYPE = 30060;

    /**
     * 注册失败消息码
     */
    Integer REGISTER_FAILED = 30070;

    /**
     * 无效登录类型消息码
     */
    Integer INVALID_LOGIN_TYPE = 30080;

    /**
     * 登录失败消息码
     */
    Integer LOGIN_FAILED = 30090;

    /**
     * 密码不可为空消息码
     */
    Integer PASSWORD_NOT_BLANK = 30100;

    /**
     * 确认密码不可为空消息码
     */
    Integer CONFIRM_PASSWORD_NOT_BLANK = 30110;

    /**
     * 两次密码不一致消息码
     */
    Integer PASSWORD_INCONSISTENT = 30120;

    /**
     * 用户名或密码错误消息码
     */
    Integer USERNAME_OR_PASSWORD_ERROR = 30130;

}