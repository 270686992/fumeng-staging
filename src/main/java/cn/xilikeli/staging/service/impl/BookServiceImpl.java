package cn.xilikeli.staging.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xilikeli.staging.common.constant.business.BookCodeConstant;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.util.Assert;
import cn.xilikeli.staging.dto.book.BookDTO;
import cn.xilikeli.staging.model.BookDO;
import cn.xilikeli.staging.repository.BookRepository;
import cn.xilikeli.staging.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 图书服务类实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void createBook(BookDTO bookDTO) {
        this.checkBookDTO(bookDTO);
        BookDO bookDO = BeanUtil.copyProperties(bookDTO, BookDO.class);
        this.bookRepository.save(bookDO);
    }

    @Override
    public BookDO getBookById(Long bookId) {
        return this.bookRepository
                .findById(bookId)
                .orElseThrow(
                        () -> new NotFoundException(BookCodeConstant.NOT_FOUND_BOOK)
                );
    }

    @Override
    public void updateBookById(Long bookId, BookDTO bookDTO) {
        BookDO bookDO = this.getBookById(bookId);
        this.checkBookDTO(bookDTO, bookDO);
        BeanUtils.copyProperties(bookDTO, bookDO);
        this.bookRepository.save(bookDO);
    }

    @Override
    public void deleteBookById(Long bookId) {
        BookDO bookDO = this.getBookById(bookId);
        this.bookRepository.delete(bookDO.getId());
    }

    @Override
    public List<BookDO> getAllBookList() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<BookDO> getBookListByPage(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return this.bookRepository.findAll(pageable);
    }

    private void checkBookDTO(BookDTO bookDTO) {
        this.checkBookDTO(bookDTO, null);
    }

    private void checkBookDTO(BookDTO bookDTO, BookDO bookDO) {
        this.checkBookTitle(bookDTO, bookDO);
    }

    private void checkBookTitle(BookDTO bookDTO, BookDO bookDO) {
        // 图书标题不能重复
        int count;
        if (bookDO == null) {
            // 添加图书时的校验
            count = this.bookRepository.countByTitle(bookDTO.getTitle());
        } else {
            // 更新图书时的校验
            count = this.bookRepository.countByTitleAndIdNot(bookDTO.getTitle(), bookDO.getId());
        }
        Assert.checkArgument(count > 0, BookCodeConstant.BOOK_TITLE_REPEAT);
    }

}