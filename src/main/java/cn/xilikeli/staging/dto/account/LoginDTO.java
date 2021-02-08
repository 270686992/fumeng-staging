package cn.xilikeli.staging.dto.account;

import cn.xilikeli.staging.common.enumeration.LoginTypeEnum;
import cn.xilikeli.staging.dto.validator.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户登录信息 DTO
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
@ApiModel(value = "用户登录信息 DTO", description = "用户登录信息 DTO")
public class LoginDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "{username.not-blank}")
    @ApiModelProperty(value = "用户名", required = true, example = "guest")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "{password.new.not-blank}")
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;

    /**
     * 登录类型
     *
     * @see cn.xilikeli.staging.common.enumeration.LoginTypeEnum
     */
    @NotNull(message = "{login-type.not-null}")
    @EnumValue(target = LoginTypeEnum.class, message = "{login-type.value}")
    @ApiModelProperty(value = "登录类型", required = true, example = "0")
    private Integer loginType;

}