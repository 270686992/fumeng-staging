package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 文件过大异常
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/28
 * @since JDK1.8
 */
@Getter
public class FileTooLargeException extends HttpException {

    private static final long serialVersionUID = -1065968361726042092L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.FILE_TOO_LARGE.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.PAYLOAD_TOO_LARGE.value();

    public FileTooLargeException() {
        super(CodeEnum.FILE_TOO_LARGE.getCode(), CodeEnum.FILE_TOO_LARGE.getDescription());
        super.defaultMessageFlag = true;
    }

    public FileTooLargeException(String message) {
        super(message);
    }

    public FileTooLargeException(Integer code) {
        super(code, CodeEnum.FILE_TOO_LARGE.getDescription());
        this.code = code;
        super.defaultMessageFlag = true;
    }

    public FileTooLargeException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}