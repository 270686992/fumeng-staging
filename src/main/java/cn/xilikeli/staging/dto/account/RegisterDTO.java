package cn.xilikeli.staging.dto.account;

import cn.xilikeli.staging.dto.validator.EqualField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户注册信息 DTO
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
@EqualField(srcField = "password", dstField = "confirmPassword", message = "{password.equal-field}")
@ApiModel(value = "用户注册信息 DTO", description = "用户注册信息 DTO")
public class RegisterDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "{username.not-blank}")
    @Length(min = 4, max = 20, message = "{username.length}")
    @ApiModelProperty(value = "用户名", example = "guest", required = true)
    private String username;

    /**
     * 用户昵称
     */
    @NotBlank(message = "{nickname.not-blank}")
    @Length(min = 2, max = 20, message = "{nickname.length}")
    @ApiModelProperty(value = "用户昵称", example = "但为君故", required = true)
    private String nickname;

    /**
     * 用户邮箱
     */
    @Email(message = "{email}")
    @NotBlank(message = "{email.not-blank}")
    @Length(min = 3, max = 50, message = "{email.length}")
    @ApiModelProperty(value = "用户邮箱", example = "123456@163.com", required = true)
    private String email;

    /**
     * 用户手机号
     */
    @NotBlank(message = "{mobile.not-blank}")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "{mobile}")
    @ApiModelProperty(value = "用户手机号", example = "18211112222", required = true, dataType = "string")
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message = "{password.new.not-blank}")
    @Pattern(regexp = "^[A-Za-z0-9_]{6,22}$", message = "{password.new.pattern}")
    @ApiModelProperty(value = "密码", example = "123456", required = true, dataType = "string")
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "{password.confirm.not-blank}")
    @ApiModelProperty(name = "confirm_password", value = "确认密码", example = "123456", required = true, dataType = "string")
    private String confirmPassword;

}