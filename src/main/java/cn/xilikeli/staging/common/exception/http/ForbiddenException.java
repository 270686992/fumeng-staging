package cn.xilikeli.staging.common.exception.http;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * 拒绝访问异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:39
 * @since JDK1.8
 */
public class ForbiddenException extends HttpException {
    private static final long serialVersionUID = 8217213750564199249L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public ForbiddenException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.FORBIDDEN.value();
    }
}