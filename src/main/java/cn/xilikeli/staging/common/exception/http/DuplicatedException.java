package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 字段重复异常
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/1/8
 * @since JDK1.8
 */
@Getter
public class DuplicatedException extends HttpException {

    private static final long serialVersionUID = -7256669023415197324L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.DUPLICATED.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.BAD_REQUEST.value();

    public DuplicatedException() {
        super(CodeEnum.DUPLICATED.getCode(), CodeEnum.DUPLICATED.getDescription());
        super.defaultMessageFlag = true;
    }

    public DuplicatedException(String message) {
        super(message);
    }

    public DuplicatedException(Integer code) {
        super(code, CodeEnum.DUPLICATED.getDescription());
        this.code = code;
        super.defaultMessageFlag = true;
    }

    public DuplicatedException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}