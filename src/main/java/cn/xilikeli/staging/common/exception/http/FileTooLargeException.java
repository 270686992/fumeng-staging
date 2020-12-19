package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 文件体积过大异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 10:47
 * @since JDK1.8
 */
public class FileTooLargeException extends HttpException {
    private static final long serialVersionUID = -1065968361726042092L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public FileTooLargeException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.PAYLOAD_TOO_LARGE.value();
    }

    /**
     * 构造函数
     *
     * @param message 自定义异常信息
     */
    public FileTooLargeException(String message) {
        super(message);
        this.code = CodeEnum.FILE_TOO_LARGE.getCode();
        this.httpStatusCode = HttpStatus.PAYLOAD_TOO_LARGE.value();
    }
}