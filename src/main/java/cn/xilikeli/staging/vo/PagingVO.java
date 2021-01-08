package cn.xilikeli.staging.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 * 分页数据统一 VO
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 16:17
 * @since JDK1.8
 */
@Data
@NoArgsConstructor
@ApiModel(value = "分页数据统一 VO", description = "封装统一分页数据结果的实体")
public class PagingVO<T> {

    /**
     * 数据总数量
     */
    @ApiModelProperty(value = "数据总数量")
    private Long total;

    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量")
    private Integer count;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private Integer page;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;

    /**
     * 数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> items;

    /**
     * 构造函数
     * 将查询出来的分页数据对象构造为一个分页数据视图对象
     *
     * @param pageT 分页数据对象
     */
    public PagingVO(Page<T> pageT) {
        this.initPageParameters(pageT);
        this.items = pageT.getContent();
    }

    /**
     * 通过分页数据对象初始化分页数据 VO 的各字段
     *
     * @param pageT 分页数据对象
     */
    protected void initPageParameters(Page<T> pageT) {
        this.total = pageT.getTotalElements();
        this.count = pageT.getSize();
        this.page = pageT.getNumber();
        this.totalPage = pageT.getTotalPages();
    }

}