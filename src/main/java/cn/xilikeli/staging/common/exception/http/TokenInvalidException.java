package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 令牌无效异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2021/1/8
 * @since JDK1.8
 */
@Getter
public class TokenInvalidException extends HttpException {

    private static final long serialVersionUID = 8997250177636961759L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.TOKEN_INVALID.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.UNAUTHORIZED.value();

    public TokenInvalidException() {
        super(CodeEnum.TOKEN_INVALID.getCode(), CodeEnum.TOKEN_INVALID.getDescription());
    }

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(Integer code) {
        super(code, CodeEnum.TOKEN_INVALID.getDescription());
        this.code = code;
    }

    public TokenInvalidException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}