package cn.xilikeli.staging.common.constant;

/**
 * <p>
 * 业务消息码常量类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public class BusinessCodeConstant {

    /**
     * 添加图书成功消息码
     */
    public static final Integer CREATE_BOOK_SUCCESS = 1000;

    /**
     * 更新图书成功消息码
     */
    public static final Integer UPDATE_BOOK_SUCCESS = 1001;

    /**
     * 删除图书成功消息码
     */
    public static final Integer DELETE_BOOK_SUCCESS = 1002;

    /**
     * 未找到相关图书消息码
     */
    public static final Integer NOT_FOUND_BOOK = 20000;

    /**
     * 图书标题重复消息码
     */
    public static final Integer BOOK_TITLE_REPEAT = 20010;

    /**
     * 注册成功消息码
     */
    public static final Integer REGISTER_SUCCESS = 1100;

    /**
     * 登录成功消息码
     */
    public static final Integer LOGIN_SUCCESS = 1101;

    /**
     * 更新用户信息成功消息码
     */
    public static final Integer UPDATE_ACCOUNT_SUCCESS = 1102;

    /**
     * 密码修改成功消息码
     */
    public static final Integer UPDATE_PASSWORD_SUCCESS = 1103;

    /**
     * 未找到相关用户消息码
     */
    public static final Integer NOT_FOUND_ACCOUNT = 30000;

    /**
     * 未找到相关用户认证信息消息码
     */
    public static final Integer NOT_FOUND_ACCOUNT_IDENTITY = 30010;

    /**
     * 用户名重复消息码
     */
    public static final Integer USERNAME_REPEAT = 30020;

    /**
     * 邮箱重复消息码
     */
    public static final Integer EMAIL_REPEAT = 30030;

    /**
     * 手机号重复消息码
     */
    public static final Integer MOBILE_REPEAT = 30040;

    /**
     * 注册失败消息码
     */
    public static final Integer REGISTER_FAILED = 30050;

    /**
     * 无效登录类型消息码
     */
    public static final Integer INVALID_LOGIN_TYPE = 30060;

    /**
     * 用户名或密码错误消息码
     */
    public static final Integer USERNAME_OR_PASSWORD_ERROR = 30070;

}