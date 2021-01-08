package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 文件信息实体
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 20:31
 * @since JDK1.8
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "文件信息实体", description = "文件信息实体")
public class File extends BaseEntity {

    private static final long serialVersionUID = -7988788433514112555L;

    /**
     * 文件 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "文件 ID")
    private Long id;

    /**
     * 文件路径
     * 文件类型为 LOCAL 时, 表示文件在本地的路径
     * 文件类型为 LOCAL 时, 表示文件在云空间(OSS)的路径
     */
    @ApiModelProperty(value = "文件路径")
    private String path;

    /**
     * 文件类型, LOCAL 表示本地, REMOTE 表示远程
     */
    @ApiModelProperty(value = "文件类型")
    private String type;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String name;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private Integer size;

    /**
     * md5 值, 防止上传重复文件
     */
    @ApiModelProperty(value = "md5 值, 防止上传重复文件")
    private String md5;

}