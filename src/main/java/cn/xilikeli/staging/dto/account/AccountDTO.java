package cn.xilikeli.staging.dto.account;

import cn.xilikeli.staging.common.enumeration.AccountStatusEnum;
import cn.xilikeli.staging.dto.validator.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

/**
 * <p>
 * 用户信息 DTO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户信息 DTO", description = "用户信息 DTO")
public class AccountDTO {

    /**
     * 用户 ID
     */
    @Positive(message = "{id.positive}")
    @ApiModelProperty(value = "用户 ID")
    private Long id;

    /**
     * 用户账号
     */
    @Length(min = 4, max = 40, message = "{account.length}")
    @ApiModelProperty(value = "用户账号", example = "guest")
    private String account;

    /**
     * 用户邮箱
     */
    @Email(message = "{email}")
    @ApiModelProperty(value = "用户邮箱", example = "123456@163.com")
    private String email;

    /**
     * 用户手机号
     */
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "{mobile}")
    @ApiModelProperty(value = "用户手机号", example = "11111111111")
    private String mobile;

    /**
     * 用户昵称
     */
    @Length(min = 2, max = 20, message = "{nickname.length}")
    @ApiModelProperty(value = "用户昵称", example = "但为君故")
    private String nickname;

    /**
     * 用户头像 url
     */
    @Length(max = 255, message = "{avatar.length}")
    @ApiModelProperty(value = "用户头像 url", example = "https://bjck.xilikeli.cn/09ec486594f346178c7fd4b82e89c124.jpg")
    private String avatar;

    /**
     * 用户状态: 1-正常, 0-冻结
     *
     * @see cn.xilikeli.staging.common.enumeration.AccountStatusEnum
     */
    @EnumValue(target = AccountStatusEnum.class, message = "{account.status}")
    @ApiModelProperty(value = "用户状态: 1-正常, 0-冻结", example = "1")
    private Integer status;

    /**
     * 用户性别, 0-男, 1-女, 2-保密
     *
     * @see cn.xilikeli.staging.common.enumeration.SexEnum
     */
    @EnumValue(target = AccountStatusEnum.class, message = "{sex}")
    @ApiModelProperty(value = "用户性别, 1-男, 0-女, 2-保密", example = "1")
    private Integer sex;

    /**
     * 用户生日
     */
    @ApiModelProperty(value = "用户生日", example = "2021-02-03 23:55:26")
    private Date birthday;

}