package cn.xilikeli.staging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 实体的抽象基类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:02
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
@ApiModel(value = "实体的抽象基类", description = "实体的抽象基类")
public abstract class BaseDO implements Serializable {

    private static final long serialVersionUID = -49953182948096835L;

    /**
     * 创建时间
     */
    @JsonIgnore
    @Column(insertable = false, updatable = false)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    @Column(insertable = false, updatable = false)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 删除时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

}