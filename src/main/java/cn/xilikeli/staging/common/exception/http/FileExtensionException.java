package cn.xilikeli.staging.common.exception.http;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 文件扩展名异常
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/28
 * @since JDK1.8
 */
@Getter
public class FileExtensionException extends HttpException {

    private static final long serialVersionUID = 6924801101407378789L;

    /**
     * 消息码
     */
    protected Integer code = CodeEnum.FILE_EXTENSION.getCode();

    /**
     * HTTP 状态码
     */
    protected Integer httpStatusCode = HttpStatus.NOT_ACCEPTABLE.value();

    public FileExtensionException() {
        super(CodeEnum.FILE_EXTENSION.getCode(), CodeEnum.FILE_EXTENSION.getDescription());
        super.defaultMessageFlag = true;
    }

    public FileExtensionException(String message) {
        super(message);
    }

    public FileExtensionException(Integer code) {
        super(code, CodeEnum.FILE_EXTENSION.getDescription());
        this.code = code;
        super.defaultMessageFlag = true;
    }

    public FileExtensionException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

}