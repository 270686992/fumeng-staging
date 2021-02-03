package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 操作失败异常
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/22
 * @since JDK1.8
 */
@Getter
public class FailedException extends HttpException {

    private static final long serialVersionUID = 3749594778843210343L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.FAIL.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public FailedException() {
        super(CodeEnum.FAIL.getCode(), CodeEnum.FAIL.getDescription());
        super.defaultMessageFlag = true;
    }

    public FailedException(String message) {
        super(message);
    }

    public FailedException(Integer code) {
        super(code, CodeEnum.FAIL.getDescription());
        this.code = code;
        super.defaultMessageFlag = true;
    }

    public FailedException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}