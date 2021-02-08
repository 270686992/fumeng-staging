package cn.xilikeli.staging.vo.account;

import cn.xilikeli.staging.dto.account.AccountDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录结果
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/6
 * @since JDK1.8
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户登录结果", description = "用户登录结果")
public class LoginResultVO extends AccountDTO {

    /**
     * token 令牌
     */
    @ApiModelProperty(value = "token 令牌")
    private String token;

}