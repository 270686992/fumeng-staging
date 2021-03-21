package cn.xilikeli.staging.vo.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户注册结果 VO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/6
 * @since JDK1.8
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户注册结果 VO", description = "用户注册结果 VO")
public class RegisterResultVO {

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Long accountId;

    /**
     * token 令牌
     */
    @ApiModelProperty(value = "token 令牌")
    private String token;

}