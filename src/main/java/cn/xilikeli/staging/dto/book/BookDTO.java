package cn.xilikeli.staging.dto.book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 图书 DTO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/3
 * @since JDK1.8
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "图书 DTO", description = "图书 DTO")
public class BookDTO {

    /**
     * 图书标题
     */
    @NotBlank(message = "{book.title.not-blank}")
    @Length(min = 1, max = 50, message = "{book.title.length}")
    @ApiModelProperty(value = "图书标题", example = "西游记", required = true)
    private String title;

    /**
     * 图书作者
     */
    @NotBlank(message = "{book.author.not-blank}")
    @Length(min = 1, max = 30, message = "{book.author.length}")
    @ApiModelProperty(value = "图书作者", example = "吴承恩", required = true)
    private String author;

    /**
     * 图书概述
     */
    @NotBlank(message = "{book.summary.not-blank}")
    @Length(min = 1, max = 1000, message = "{book.summary.length}")
    @ApiModelProperty(value = "图书概述", example = "四大名著之一", required = true)
    private String summary;

    /**
     * 图书封面图 url
     */
    @Length(max = 255, message = "{book.image.length}")
    @ApiModelProperty(value = "图书封面图 url", example = "https://bjck.xilikeli.cn/09ec486594f346178c7fd4b82e89c124.jpg")
    private String image;

}