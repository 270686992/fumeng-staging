package cn.xilikeli.staging.common.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * JPA 分页工具类
 * 列表数据转换为分页对象
 * 参考自 CSDN: https://blog.csdn.net/weixx3/article/details/103348630
 * </p>
 *
 * @author 踏雪彡寻梅@浮生若梦
 * @version 1.0
 * @date 2020/9/24 - 16:11
 * @since JDK1.8
 */
public class JpaPageUtil {
    /**
     * 根据 pageable, 将一个 List 转换为一个 Page
     * pageSize 每页数量以 pageable 中为准
     * page 从 0 开始
     */
    public static <T> Page<T> listToPage(List<T> list, Pageable pageable) {
        return getPage(list, pageable);
    }

    /**
     * 根据 page, 将一个 List 转换为一个 Page
     * pageSize 每页数量为 15
     * page 从 0 开始
     */
    public static <T> Page<T> listToPage(List<T> list, int page) {
        PageRequest pageable = PageRequest.of(page, 15);
        return getPage(list, pageable);
    }

    private static <T> Page<T> getPage(List<T> list, Pageable pageable) {
        // 第 n 页起始值
        int pageStart = pageable.getPageNumber() * pageable.getPageSize();
        // 第 n 页期望结尾值
        int expectPageEnd = pageStart + pageable.getPageSize() - 1;
        return Optional.of(list.size())
                .filter(size -> size >= pageStart)
                .map(size -> getRealSubList(pageStart, expectPageEnd, list, pageable))
                .orElseGet(() -> getEmptySubList(list, pageable));
    }

    private static <T> PageImpl<T> getRealSubList(int pageStart, int expectPageEnd, List<T> list, Pageable pageable) {
        int realPageEnd = (list.size() > expectPageEnd) ? expectPageEnd + 1 : list.size();
        return new PageImpl<>(list.subList(pageStart, realPageEnd), pageable, list.size());
    }

    private static <T> PageImpl<T> getEmptySubList(List<T> list, Pageable pageable) {
        return new PageImpl<>(new ArrayList(), pageable, list.size());
    }
}