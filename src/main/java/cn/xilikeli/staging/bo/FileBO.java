package cn.xilikeli.staging.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 文件信息 BO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/28
 * @since JDK1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "文件信息 BO", description = "文件信息 BO")
public class FileBO {

    /**
     * 文件 ID
     */
    @ApiModelProperty(value = "文件 ID")
    private Long id;

    /**
     * 文件 key, 上传时指定的
     */
    @ApiModelProperty(value = "文件 key, 上传时指定的")
    private String key;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String path;

    /**
     * 文件 URL
     */
    @ApiModelProperty(value = "文件 URL")
    private String url;

}