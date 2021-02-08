package cn.xilikeli.staging.dto.account;

import cn.xilikeli.staging.dto.validator.EqualField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 修改密码 DTO
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
@EqualField(srcField = "newPassword", dstField = "confirmPassword", message = "{password.equal-field}")
@ApiModel(value = "修改密码 DTO", description = "修改密码 DTO")
public class ChangePasswordDTO {

    /**
     * 新密码
     */
    @NotBlank(message = "{password.new.not-blank}")
    @Pattern(regexp = "^[A-Za-z0-9_]{6,22}$", message = "{password.new.pattern}")
    @ApiModelProperty(value = "新密码", example = "123456", required = true)
    private String newPassword;

    /**
     * 确认密码
     */
    @NotBlank(message = "{password.confirm.not-blank}")
    @ApiModelProperty(value = "确认密码", example = "123456", required = true)
    private String confirmPassword;

    /**
     * 旧密码
     */
    @NotBlank(message = "{password.old.not-blank}")
    @ApiModelProperty(value = "旧密码", example = "123456", required = true)
    private String oldPassword;

}