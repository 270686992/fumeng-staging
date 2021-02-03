package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 参数错误异常
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/22
 * @since JDK1.8
 */
@Getter
public class ParameterException extends HttpException {

    private static final long serialVersionUID = 2078393748938417226L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.PARAMETER_ERROR.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.BAD_REQUEST.value();

    public ParameterException() {
        super(CodeEnum.PARAMETER_ERROR.getCode(), CodeEnum.PARAMETER_ERROR.getDescription());
        super.defaultMessageFlag = true;
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(Integer code) {
        super(code, CodeEnum.PARAMETER_ERROR.getDescription());
        this.code = code;
        super.defaultMessageFlag = true;
    }

    public ParameterException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}