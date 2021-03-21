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
     * 账号, 如: 用户名、微信小程序 openid 等等...
     */
    @NotBlank(message = "{account.not-blank}")
    @ApiModelProperty(value = "账号, 如: 用户名、微信小程序 openid 等等...", required = true, example = "guest")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    /**
     * 登录类型
     *
     * @see cn.xilikeli.staging.common.enumeration.LoginTypeEnum
     */
    @NotNull(message = "{login-type.not-null}")
    @EnumValue(target = LoginTypeEnum.class, message = "{login-type.value}")
    @ApiModelProperty(value = "登录类型: 0(用户名、密码登录), 1(微信小程序登录), 2(邮箱登录), 3(手机号登录)", required = true, example = "0")
    private Integer loginType;

}