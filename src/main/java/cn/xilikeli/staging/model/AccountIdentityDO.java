package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 用户认证信息实体
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/1/14
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "account_identity")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "用户认证信息实体", description = "用户认证信息实体")
public class AccountIdentityDO extends BaseDO {

    private static final long serialVersionUID = 3763210398045163760L;

    /**
     * 用户认证信息 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "用户认证信息 ID")
    private Long id;

    /**
     * 所属用户 ID
     */
    @ApiModelProperty(value = "所属用户 ID")
    private Long accountId;

    /**
     * 登录类型(手机号, 邮箱, 用户名或第三方应用名称(微信, 微博, QQ 等)
     */
    @ApiModelProperty(value = "登录类型(手机号, 邮箱, 用户名或第三方应用名称(微信, 微博, QQ 等)")
    private String identityType;

    /**
     * 标识(手机号, 邮箱, 用户名或第三方应用的唯一标识)
     */
    @ApiModelProperty(value = "标识(手机号, 邮箱, 用户名或第三方应用的唯一标识)")
    private String identifier;

    /**
     * 密码凭证(站内的保存密码, 站外的不保存或保存 token)
     */
    @ApiModelProperty(value = "密码凭证(站内的保存密码, 站外的不保存或保存 token)")
    private String credential;

}