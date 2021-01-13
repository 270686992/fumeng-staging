package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.common.constant.BusinessCodeConstant;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.model.Book;
import cn.xilikeli.staging.repository.BookRepository;
import cn.xilikeli.staging.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 图书服务类实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 01:29
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
    public Book getBookById(Long bookId) {
        Optional<Book> bookOptional = this.bookRepository.findById(bookId);
        return bookOptional.orElseThrow(
                () -> new NotFoundException(BusinessCodeConstant.NOT_FOUND_BOOK)
        );
    }

    @Override
    public List<Book> getAllBookList() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> getBookListByPage(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return this.bookRepository.findAll(pageable);
    }

}