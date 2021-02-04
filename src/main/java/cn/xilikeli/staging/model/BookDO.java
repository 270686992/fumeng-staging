package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 图书实体
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "book")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "图书实体", description = "图书实体")
public class BookDO extends BaseDO {

    private static final long serialVersionUID = -187723241117592869L;

    /**
     * 图书 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
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
     * 图书概述
     */
    @ApiModelProperty(value = "图书概述")
    private String summary;

    /**
     * 图书封面图 url
     */
    @ApiModelProperty(value = "图书封面图 url")
    private String image;

}