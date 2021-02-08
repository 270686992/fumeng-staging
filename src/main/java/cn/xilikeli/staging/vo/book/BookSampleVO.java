package cn.xilikeli.staging.vo.book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 图书示例 VO
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
@ApiModel(value = "图书示例 VO", description = "图书示例 VO")
public class BookSampleVO {

    /**
     * 图书 ID
     */
    @ApiModelProperty(value = "图书 ID")
    private Long id;

    /**
     * 图书标题
     */
    @ApiModelProperty(value = "图书标题")
    private String title;

    /**
     * 图书作者
     */
    @ApiModelProperty(value = "图书作者")
    private String author;

    /**
     * 图书封面图 url
     */
    @ApiModelProperty(value = "图书封面图 url")
    private String image;

}