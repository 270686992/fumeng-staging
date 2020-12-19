package cn.xilikeli.staging.common.exception.http;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * 操作失败异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:38
 * @since JDK1.8
 */
public class FailedException extends HttpException {
    private static final long serialVersionUID = 3749594778843210343L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public FailedException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}