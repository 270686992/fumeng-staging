package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * HTTP 自定义异常基类
 * 其子类用于处理各种情况的请求发生的异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:37
 * @since JDK1.8
 */
@Getter
public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 9135555571049693967L;

    /**
     * 消息码
     */
    protected Integer code;

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode;

    /**
     * 默认构造函数
     */
    public HttpException() {
        super(CodeEnum.INTERNAL_SERVER_ERROR.getDescription());
        this.code = CodeEnum.INTERNAL_SERVER_ERROR.getCode();
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    /**
     * 构造函数
     *
     * @param message 自定义异常信息
     */
    public HttpException(String message) {
        super(message);
        this.code = CodeEnum.INTERNAL_SERVER_ERROR.getCode();
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}