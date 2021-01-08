package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 方法不允许异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2021/1/8
 * @since JDK1.8
 */
@Getter
public class MethodNotAllowedException extends HttpException {

    private static final long serialVersionUID = 3537087993223406395L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.METHOD_NOT_ALLOWED.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.METHOD_NOT_ALLOWED.value();

    public MethodNotAllowedException() {
        super(CodeEnum.METHOD_NOT_ALLOWED.getCode(), CodeEnum.METHOD_NOT_ALLOWED.getDescription());
    }

    public MethodNotAllowedException(String message) {
        super(message);
    }

    public MethodNotAllowedException(Integer code) {
        super(code, CodeEnum.METHOD_NOT_ALLOWED.getDescription());
        this.code = code;
    }

    public MethodNotAllowedException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}