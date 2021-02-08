package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.constant.BusinessCodeConstant;
import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.dto.book.BookDTO;
import cn.xilikeli.staging.model.BookDO;
import cn.xilikeli.staging.service.BookService;
import cn.xilikeli.staging.vo.book.BookSampleVO;
import cn.xilikeli.staging.vo.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <p>
 * 图书 API 接口控制器
 * 示例 Demo, 图书表 SQL 位于项目根目录下的 profile/sql 目录中
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Validated
@RestController
@RequestMapping("/v1/book")
@Api(value = "图书 API 接口", tags = {"图书业务的相关接口"})
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 添加一本图书
     *
     * @param bookDTO 图书 DTO
     * @return 添加成功返回成功的响应结果, 否则返回失败的响应结果
     */
    @ScopeLevel
    @PostMapping("")
    @ApiOperation(value = "添加图书接口", notes = "添加一本图书", httpMethod = "POST")
    public CreatedResponseVO<Object> createBook(@RequestBody
                                                @Validated BookDTO bookDTO) {
        this.bookService.createBook(bookDTO);
        return new CreatedResponseVO<>(BusinessCodeConstant.CREATE_BOOK_SUCCESS);
    }

    /**
     * 根据图书 ID 获取相应图书的信息
     *
     * @param bookId 图书 ID
     * @return 返回获取的图书的信息
     */
    @ScopeLevel
    @GetMapping("/{id}")
    @ApiOperation(value = "查看图书详情接口", notes = "根据图书 ID 获取相应图书的信息", httpMethod = "GET")
    public BookDO getBookById(@PathVariable(name = "id")
                              @Positive(message = "{id.positive}")
                              @ApiParam(name = "id", value = "图书 ID", required = true, example = "1") Long bookId) {
        return this.bookService.getBookById(bookId);
    }

    /**
     * 根据图书 ID 更新该图书的信息
     *
     * @param bookId  图书 ID
     * @param bookDTO 图书 DTO
     * @return 更新成功返回成功的响应结果, 否则返回失败的响应结果
     */
    @ScopeLevel
    @PutMapping("/{id}")
    @ApiOperation(value = "更新图书接口", notes = "根据图书 ID 更新该图书的信息", httpMethod = "PUT")
    public UpdatedResponseVO<Object> updateBookById(@PathVariable(name = "id")
                                                    @Positive(message = "{id.positive}")
                                                    @ApiParam(name = "id", value = "图书 ID", required = true, example = "1") Long bookId,
                                                    @RequestBody
                                                    @Validated BookDTO bookDTO) {
        this.bookService.updateBookById(bookId, bookDTO);
        return new UpdatedResponseVO<>(BusinessCodeConstant.UPDATE_BOOK_SUCCESS);
    }

    /**
     * 根据图书 ID 删除相应图书
     *
     * @param bookId 图书 ID
     * @return 删除成功返回成功的响应结果, 否则返回失败的响应结果
     */
    @ScopeLevel
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除图书接口", notes = "根据图书 ID 删除相应图书", httpMethod = "DELETE")
    public DeletedResponseVO<Object> deleteBookById(@PathVariable(name = "id")
                                                    @Positive(message = "{id.positive}")
                                                    @ApiParam(name = "id", value = "图书 ID", required = true, example = "1") Long bookId) {
        this.bookService.deleteBookById(bookId);
        return new DeletedResponseVO<>(BusinessCodeConstant.DELETE_BOOK_SUCCESS);
    }

    /**
     * 获取所有图书
     *
     * @return 返回包含所有图书的 List 列表
     */
    @ScopeLevel
    @GetMapping("/list")
    @ApiOperation(value = "获取所有图书接口", notes = "获取所有图书, 以 List 列表方式获取", httpMethod = "GET")
    public List<BookDO> getAllBookList() {
        return this.bookService.getAllBookList();
    }

    /**
     * 获取当前页的图书列表
     *
     * @param page  当前页数
     * @param count 每页图书数
     * @return 返回封装着获取的当前页的图书列表的分页视图对象
     */
    @ScopeLevel
    @GetMapping("/latest")
    @ApiOperation(value = "分页获取图书列表接口", notes = "获取当前页的图书列表", httpMethod = "GET")
    public PagingVO<BookDO> getBookListByPage(@RequestParam(name = "page", required = false, defaultValue = "0")
                                              @Min(value = 0, message = "{page.number.min}")
                                              @ApiParam(name = "page", value = "当前页数", defaultValue = "0", example = "0") Integer page,
                                              @RequestParam(name = "count", required = false, defaultValue = "10")
                                              @Min(value = 1, message = "{page.count.min}")
                                              @Max(value = 30, message = "{page.count.max}")
                                              @ApiParam(name = "count", value = "每页图书数", defaultValue = "10", example = "10") Integer count) {
        Page<BookDO> bookPage = this.bookService.getBookListByPage(page, count);
        return new PagingVO<>(bookPage);
    }

    /**
     * 获取当前页的图书简要信息列表
     *
     * @param page  当前页数
     * @param count 每页图书简要信息数
     * @return 返回封装着获取的当前页的图书简要信息列表的分页视图对象
     */
    @ScopeLevel
    @GetMapping("/simple/latest")
    @ApiOperation(value = "分页获取图书简要信息列表接口", notes = "获取当前页的图书简要信息列表", httpMethod = "GET")
    public PagingDozerVO<BookDO, BookSampleVO> getSimpleBookListByPage(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                       @Min(value = 0, message = "{page.number.min}")
                                                                       @ApiParam(name = "page", value = "当前页数", defaultValue = "0", example = "0") Integer page,
                                                                       @RequestParam(name = "count", required = false, defaultValue = "10")
                                                                       @Min(value = 1, message = "{page.count.min}")
                                                                       @Max(value = 30, message = "{page.count.max}")
                                                                       @ApiParam(name = "count", value = "每页图书简要信息数", defaultValue = "10", example = "10") Integer count) {
        Page<BookDO> bookPage = this.bookService.getBookListByPage(page, count);
        return new PagingDozerVO<>(bookPage, BookSampleVO.class);
    }

}