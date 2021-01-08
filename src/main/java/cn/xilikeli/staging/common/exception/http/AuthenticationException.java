package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 认证异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:41
 * @since JDK1.8
 */
@Getter
public class AuthenticationException extends HttpException {

    private static final long serialVersionUID = 1318423607224701999L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.UN_AUTHENTICATION.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.UNAUTHORIZED.value();


    public AuthenticationException() {
        super(CodeEnum.UN_AUTHENTICATION.getCode(), CodeEnum.UN_AUTHENTICATION.getDescription());
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Integer code) {
        super(code, CodeEnum.UN_AUTHENTICATION.getDescription());
        this.code = code;
    }

    public AuthenticationException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}