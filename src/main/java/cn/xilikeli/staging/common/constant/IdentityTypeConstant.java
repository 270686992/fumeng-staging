package cn.xilikeli.staging.common.constant;

/**
 * <p>
 * 身份认证类型常量
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/5
 * @since JDK1.8
 */
public interface IdentityTypeConstant {

    /**
     * 表示通过用户名和密码来进行身份认证
     */
    String USERNAME_PASSWORD_IDENTITY = "USERNAME_PASSWORD";

    /**
     * 表示通过微信小程序 openid 来进行身份认证
     */
    String WE_CHAT_APPLETS_OPENID_IDENTITY = "WE_CHAT_APPLETS_OPENID";

}