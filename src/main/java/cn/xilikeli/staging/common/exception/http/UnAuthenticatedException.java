package cn.xilikeli.staging.common.exception.http;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * 未认证异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:41
 * @since JDK1.8
 */
public class UnAuthenticatedException extends HttpException {
    private static final long serialVersionUID = 1318423607224701999L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public UnAuthenticatedException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.UNAUTHORIZED.value();
    }
}