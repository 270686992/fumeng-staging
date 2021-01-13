package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 用户基本信息实体
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2021/1/14
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "account")
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "用户基本信息实体", description = "用户基本信息实体")
public class AccountDO extends BaseDO {

    private static final long serialVersionUID = 8853465350295250712L;

    /**
     * 用户 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "用户 ID")
    private Long id;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 用户头像 url
     */
    @ApiModelProperty(value = "用户头像 url")
    private String avatar;

    /**
     * 用户状态: 1-正常, 0-冻结
     */
    @ApiModelProperty(value = "用户状态: 1-正常, 0-冻结")
    private Integer status;

    /**
     * 用户性别, 1-男, 0-女, 2-保密
     */
    @ApiModelProperty(value = "用户性别, 1-男, 0-女, 2-保密")
    private Integer sex;

    /**
     * 用户生日
     */
    @ApiModelProperty(value = "用户生日")
    private Date birthday;

}