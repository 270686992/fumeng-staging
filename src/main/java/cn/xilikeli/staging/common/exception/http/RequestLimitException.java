package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 请求过多异常
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/1/8
 * @since JDK1.8
 */
@Getter
public class RequestLimitException extends HttpException {

    private static final long serialVersionUID = 435974574855158823L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.REQUEST_LIMIT.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.TOO_MANY_REQUESTS.value();

    public RequestLimitException() {
        super(CodeEnum.REQUEST_LIMIT.getCode(), CodeEnum.REQUEST_LIMIT.getDescription());
        super.defaultMessageFlag = true;
    }

    public RequestLimitException(String message) {
        super(message);
    }

    public RequestLimitException(Integer code) {
        super(code, CodeEnum.REQUEST_LIMIT.getDescription());
        this.code = code;
        super.defaultMessageFlag = true;
    }

    public RequestLimitException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}