package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 令牌过期异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2021/1/8
 * @since JDK1.8
 */
@Getter
public class TokenExpiredException extends HttpException {

    private static final long serialVersionUID = -3464191053538372978L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.TOKEN_EXPIRED.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.UNAUTHORIZED.value();

    public TokenExpiredException() {
        super(CodeEnum.TOKEN_EXPIRED.getCode(), CodeEnum.TOKEN_EXPIRED.getDescription());
    }

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(Integer code) {
        super(code, CodeEnum.TOKEN_EXPIRED.getDescription());
        this.code = code;
    }

    public TokenExpiredException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}