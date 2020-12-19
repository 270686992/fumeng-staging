package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 文件类型不支持异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 10:45
 * @since JDK1.8
 */
public class FileExtensionException extends HttpException {
    private static final long serialVersionUID = 6924801101407378789L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public FileExtensionException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.NOT_ACCEPTABLE.value();
    }

    /**
     * 构造函数
     *
     * @param message 自定义异常信息
     */
    public FileExtensionException(String message) {
        super(message);
        this.code = CodeEnum.FILE_EXTENSION.getCode();
        this.httpStatusCode = HttpStatus.NOT_ACCEPTABLE.value();
    }
}