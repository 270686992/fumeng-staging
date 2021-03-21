package cn.xilikeli.staging.common.constant.business;

/**
 * <p>
 * 图书业务消息码常量
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/3/21
 * @since JDK1.8
 */
public interface BookCodeConstant {

    /**
     * 添加图书成功消息码
     */
    Integer CREATE_BOOK_SUCCESS = 1000;

    /**
     * 更新图书成功消息码
     */
    Integer UPDATE_BOOK_SUCCESS = 1001;

    /**
     * 删除图书成功消息码
     */
    Integer DELETE_BOOK_SUCCESS = 1002;

    /**
     * 未找到相关图书消息码
     */
    Integer NOT_FOUND_BOOK = 20000;

    /**
     * 图书标题重复消息码
     */
    Integer BOOK_TITLE_REPEAT = 20010;

}