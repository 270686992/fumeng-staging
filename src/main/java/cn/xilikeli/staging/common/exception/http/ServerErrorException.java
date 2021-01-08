package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 服务器内部错误异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:40
 * @since JDK1.8
 */
@Getter
public class ServerErrorException extends HttpException {

    private static final long serialVersionUID = 294268559090017482L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.INTERNAL_SERVER_ERROR.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public ServerErrorException() {
        super(CodeEnum.INTERNAL_SERVER_ERROR.getCode(), CodeEnum.INTERNAL_SERVER_ERROR.getDescription());
    }

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(Integer code) {
        super(code, CodeEnum.INTERNAL_SERVER_ERROR.getDescription());
        this.code = code;
    }

    public ServerErrorException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}