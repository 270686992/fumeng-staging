package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * HTTP 自定义异常基类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/22
 * @since JDK1.8
 */
@Getter
public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 9135555571049693967L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.INTERNAL_SERVER_ERROR.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**
     * 是否是默认消息标记
     * <p>
     * true: 没有通过构造函数传入 message
     * <p>
     * false: 通过构造函数传入了 message
     */
    protected Boolean defaultMessageFlag = true;

    public HttpException() {
        super(CodeEnum.INTERNAL_SERVER_ERROR.getDescription());
    }

    public HttpException(String message) {
        super(message);
        this.defaultMessageFlag = false;
    }

    public HttpException(Integer code) {
        super(CodeEnum.INTERNAL_SERVER_ERROR.getDescription());
        this.code = code;
    }

    public HttpException(Integer code, Integer httpStatusCode) {
        super(CodeEnum.INTERNAL_SERVER_ERROR.getDescription());
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public HttpException(Integer code, String message) {
        super(message);
        this.code = code;
        this.defaultMessageFlag = false;
    }

    public HttpException(Integer code, String message, Integer httpStatusCode) {
        super(message);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.defaultMessageFlag = false;
    }

    public HttpException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public HttpException(Throwable cause, Integer code, Integer httpStatusCode) {
        super(cause);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
        this.defaultMessageFlag = false;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

}