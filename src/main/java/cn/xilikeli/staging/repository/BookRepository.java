package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.BookDO;

/**
 * <p>
 * 图书 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public interface BookRepository extends BaseRepository<BookDO, Long> {

    /**
     * 通过图书标题查询存在该图书标题的图书的数量
     *
     * @param title 图书标题
     * @return 返回相应的图书数量, 查询不到则会返回 0
     */
    int countByTitle(String title);

    /**
     * 通过图书 ID 和图书标题查询除了该图书 ID 之外的存在该图书标题的图书的数量
     *
     * @param id    图书 ID
     * @param title 图书标题
     * @return 返回相应的图书数量, 查询不到则会返回 0
     */
    int countByTitleAndIdNot(String title, Long id);

}