package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 刷新令牌失败异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2021/1/8
 * @since JDK1.8
 */
@Getter
public class RefreshTokenFailedException extends HttpException {

    private static final long serialVersionUID = 4777689057225416382L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.REFRESH_TOKEN_FAILED.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.UNAUTHORIZED.value();

    public RefreshTokenFailedException() {
        super(CodeEnum.REFRESH_TOKEN_FAILED.getCode(), CodeEnum.REFRESH_TOKEN_FAILED.getDescription());
    }

    public RefreshTokenFailedException(String message) {
        super(message);
    }

    public RefreshTokenFailedException(Integer code) {
        super(code, CodeEnum.REFRESH_TOKEN_FAILED.getDescription());
        this.code = code;
    }

    public RefreshTokenFailedException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}