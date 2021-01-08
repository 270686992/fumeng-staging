package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 文件数量太多异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 10:39
 * @since JDK1.8
 */
@Getter
public class FileTooManyException extends HttpException {

    private static final long serialVersionUID = 1716392625715710138L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.FILE_TOO_MANY.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.PAYLOAD_TOO_LARGE.value();

    public FileTooManyException() {
        super(CodeEnum.FILE_TOO_MANY.getCode(), CodeEnum.FILE_TOO_MANY.getDescription());
    }

    public FileTooManyException(String message) {
        super(message);
    }

    public FileTooManyException(Integer code) {
        super(code, CodeEnum.FILE_TOO_MANY.getDescription());
        this.code = code;
    }

    public FileTooManyException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}