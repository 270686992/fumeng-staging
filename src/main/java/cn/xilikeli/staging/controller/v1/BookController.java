package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.util.ResponseUtil;
import cn.xilikeli.staging.model.Book;
import cn.xilikeli.staging.service.BookService;
import cn.xilikeli.staging.vo.BookSampleVO;
import cn.xilikeli.staging.vo.PagingDozerVO;
import cn.xilikeli.staging.vo.PagingVO;
import cn.xilikeli.staging.vo.UnifyResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
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
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 01:29
 * @since JDK1.8
 */
@Validated
@RestController
@RequestMapping("/v1/book")
@AllArgsConstructor
@Api(value = "图书 API 接口", tags = {"图书业务的相关接口"})
public class BookController {
    private final BookService bookService;

    /**
     * 根据图书 ID 获取相应图书
     *
     * @param bookId 图书 ID
     * @return 返回获取的图书
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取一本图书", notes = "根据图书 ID 获取相应图书", httpMethod = "GET")
    public UnifyResponseVO<Book> getBook(@PathVariable(name = "id")
                                         @Positive(message = "{id.positive}")
                                         @ApiParam(name = "id", value = "图书 ID", required = true) Long bookId) {
        Book book = this.bookService.getBookById(bookId);
        return ResponseUtil.generateUnifyResponseVO(book);
    }

    /**
     * 获取所有图书
     *
     * @return 返回包含所有图书的 List 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取所有图书", notes = "获取所有图书, 以 List 列表方式获取", httpMethod = "GET")
    public UnifyResponseVO<List<Book>> getAllBookList() {
        List<Book> bookList = this.bookService.getAllBookList();
        return ResponseUtil.generateUnifyResponseVO(bookList);
    }

    /**
     * 根据分页查询参数 page、count 获取当前页的图书列表
     *
     * @param page  当前页数
     * @param count 每页图书数
     * @return 返回封装着获取的图书列表的分页对象
     */
    @GetMapping("/latest")
    @ApiOperation(value = "分页获取图书", notes = "根据分页查询参数 page、count 获取当前页的图书列表", httpMethod = "GET")
    public UnifyResponseVO<PagingVO<Book>> getBookList(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                       @Min(value = 0, message = "{page.number.min}")
                                                       @ApiParam(name = "page", value = "当前页数", defaultValue = "0") Integer page,
                                                       @RequestParam(name = "count", required = false, defaultValue = "10")
                                                       @Min(value = 1, message = "{page.count.min}")
                                                       @Max(value = 30, message = "{page.count.max}")
                                                       @ApiParam(name = "count", value = "每页图书数", defaultValue = "10") Integer count) {
        // 获取分页对象
        Page<Book> bookPage = this.bookService.getBookListByPage(page, count);
        // 转换为分页 VO
        PagingVO<Book> bookPagingVO = new PagingVO<>(bookPage);
        return ResponseUtil.generateUnifyResponseVO(bookPagingVO);
    }

    /**
     * 根据分页查询参数 page、count 获取当前页的图书简要信息列表
     *
     * @param page  当前页数
     * @param count 每页图书简要信息数
     * @return 返回封装着获取的图书简要信息列表的分页对象
     */
    @GetMapping("/simple/latest")
    @ApiOperation(value = "分页获取图书简要信息", notes = "根据分页查询参数 page、count 获取当前页的图书简要信息列表", httpMethod = "GET")
    public UnifyResponseVO<PagingDozerVO<Book, BookSampleVO>> getSimpleBookList(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                                @Min(value = 0, message = "{page.number.min}")
                                                                                @ApiParam(name = "page", value = "当前页数", defaultValue = "0") Integer page,
                                                                                @RequestParam(name = "count", required = false, defaultValue = "10")
                                                                                @Min(value = 1, message = "{page.count.min}")
                                                                                @Max(value = 30, message = "{page.count.max}")
                                                                                @ApiParam(name = "count", value = "每页图书简要信息数", defaultValue = "10") Integer count) {
        // 获取分页对象
        Page<Book> bookPage = this.bookService.getBookListByPage(page, count);
        // 转换为分页 VO
        PagingDozerVO<Book, BookSampleVO> bookPagingVO = new PagingDozerVO<>(bookPage, BookSampleVO.class);
        return ResponseUtil.generateUnifyResponseVO(bookPagingVO);
    }
}