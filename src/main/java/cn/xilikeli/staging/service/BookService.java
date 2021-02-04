package cn.xilikeli.staging.service;

import cn.xilikeli.staging.dto.book.BookDTO;
import cn.xilikeli.staging.model.BookDO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 * 图书服务类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public interface BookService {

    /**
     * 添加一本图书
     *
     * @param bookDTO 图书 DTO
     */
    void createBook(BookDTO bookDTO);

    /**
     * 根据图书 ID 获取相应图书
     *
     * @param bookId 图书 ID
     * @return 返回获取的图书
     */
    BookDO getBookById(Long bookId);

    /**
     * 根据图书 ID 更新该图书的信息
     *
     * @param bookId  图书 ID
     * @param bookDTO 图书 DTO
     */
    void updateBookById(Long bookId, BookDTO bookDTO);

    /**
     * 根据图书 ID 删除相应图书
     *
     * @param bookId 图书 ID
     */
    void deleteBookById(Long bookId);

    /**
     * 获取所有图书
     *
     * @return 返回包含所有图书的 List 列表, 如果没有图书则会返回空列表
     */
    List<BookDO> getAllBookList();

    /**
     * 获取当前页的图书列表
     *
     * @param page  当前页数
     * @param count 每页图书数
     * @return 返回封装着获取的当前页的图书列表(没有图书则会为空列表)的分页对象
     */
    Page<BookDO> getBookListByPage(Integer page, Integer count);

}