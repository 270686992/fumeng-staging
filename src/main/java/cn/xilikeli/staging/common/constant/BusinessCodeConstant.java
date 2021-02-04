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
}