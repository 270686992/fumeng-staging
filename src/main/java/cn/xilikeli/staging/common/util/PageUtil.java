package cn.xilikeli.staging.common.util;

import cn.xilikeli.staging.bo.PageCounterBO;

/**
 * <p>
 * 分页工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 16:12
 * @since JDK1.8
 */
public class PageUtil {

    /**
     * 分页参数转换, 移动端分页参数转换为 PC 端分页参数
     *
     * @param start 当前数据索引
     * @param count 每页的数据条数
     * @return 返回转换后的统一分页参数 BO
     */
    public static PageCounterBO convertMobilePage2PcPage(Integer start, Integer count) {
        Integer page = start / count;
        return PageCounterBO.builder()
                .page(page)
                .count(count)
                .build();
    }

}