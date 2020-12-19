package cn.xilikeli.staging.common.exception.http;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * 服务器内部错误异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:40
 * @since JDK1.8
 */
public class ServerErrorException extends HttpException {
    private static final long serialVersionUID = 294268559090017482L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public ServerErrorException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}