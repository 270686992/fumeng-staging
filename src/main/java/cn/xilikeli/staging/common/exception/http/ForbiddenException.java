package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 禁止操作异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:39
 * @since JDK1.8
 */
@Getter
public class ForbiddenException extends HttpException {

    private static final long serialVersionUID = 8217213750564199249L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.FORBIDDEN.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.FORBIDDEN.value();

    public ForbiddenException() {
        super(CodeEnum.FORBIDDEN.getCode(), CodeEnum.FORBIDDEN.getDescription());
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Integer code) {
        super(code, CodeEnum.FORBIDDEN.getDescription());
        this.code = code;
    }

    public ForbiddenException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}