package cn.xilikeli.staging.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 统一分页参数 BO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "统一分页参数 BO", description = "封装统一的分页参数的实体")
public class PageCounterBO {

    /**
     * 移动端 start 参数转换后的当前页数
     */
    @ApiModelProperty(value = "移动端 start 参数转换后的当前页数")
    private Integer page;

    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量")
    private Integer count;

}