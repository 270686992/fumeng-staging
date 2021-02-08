package cn.xilikeli.staging.dto.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * token 令牌信息 DTO
 * <p>
 * 用于封装着前端传来的 token 令牌信息
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
@ApiModel(value = "token 令牌信息 DTO", description = "用于封装着前端传来的 token 令牌信息")
public class TokenDTO {

    /**
     * token 令牌信息
     */
    @NotBlank(message = "{token.not-blank}")
    @ApiModelProperty(value = "token 令牌信息", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEyLCJzY29wZSI6OCwiZXhwIjoxNjk5MDAxOTczLCJpYXQiOjE2MTI2MDE5NzN9.sWGLbZYc9GxoTfFIuKVi3de2hOcQqLC68zfUvSfZ3xA", required = true)
    private String token;

}