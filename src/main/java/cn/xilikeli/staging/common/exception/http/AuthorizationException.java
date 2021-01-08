package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 授权异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2021/1/8
 * @since JDK1.8
 */
@Getter
public class AuthorizationException extends HttpException {

    private static final long serialVersionUID = -3447601408967005971L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.UN_AUTHORIZATION.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.FORBIDDEN.value();

    public AuthorizationException() {
        super(CodeEnum.UN_AUTHORIZATION.getCode(), CodeEnum.UN_AUTHORIZATION.getDescription());
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(Integer code) {
        super(code, CodeEnum.UN_AUTHORIZATION.getDescription());
        this.code = code;
    }

    public AuthorizationException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}