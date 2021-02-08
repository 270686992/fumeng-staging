package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 资源不存在异常
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/22
 * @since JDK1.8
 */
@Getter
public class NotFoundException extends HttpException {

    private static final long serialVersionUID = 2432756194955583275L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.NOT_FOUND.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.NOT_FOUND.value();

    public NotFoundException() {
        super(CodeEnum.NOT_FOUND.getCode(), CodeEnum.NOT_FOUND.getDescription());
        super.defaultMessageFlag = true;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Integer code) {
        super(code, CodeEnum.NOT_FOUND.getDescription());
        this.code = code;
        super.defaultMessageFlag = true;
    }

    public NotFoundException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}