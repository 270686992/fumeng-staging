package cn.xilikeli.staging.dto.account;

import cn.xilikeli.staging.common.enumeration.RegisterTypeEnum;
import cn.xilikeli.staging.dto.validator.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "用户注册信息 DTO", description = "用户注册信息 DTO")
public class RegisterDTO {

    /**
     * 账号, 如: 用户名、微信小程序 openid 等等...
     */
    @NotBlank(message = "{account.not-blank}")
    @Length(min = 4, max = 40, message = "{account.length}")
    @ApiModelProperty(value = "账号, 如: 用户名、微信小程序 openid 等等...", example = "guest", required = true)
    private String account;

    /**
     * 密码
     */
    @Pattern(regexp = "^[A-Za-z0-9_]{6,22}$", message = "{password.new.pattern}")
    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(name = "confirm_password", value = "确认密码", example = "123456")
    private String confirmPassword;

    /**
     * 注册类型
     *
     * @see cn.xilikeli.staging.common.enumeration.RegisterTypeEnum
     */
    @NotNull(message = "{register-type.not-null}")
    @EnumValue(target = RegisterTypeEnum.class, message = "{register-type.value}")
    @ApiModelProperty(value = "注册类型: 0(用户名、密码注册), 1(微信小程序注册), 2(邮箱注册), 3(手机号注册)", required = true, example = "0")
    private Integer registerType;

}