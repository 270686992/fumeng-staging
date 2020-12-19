package cn.xilikeli.staging.common.exception.http;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * 请求参数错误异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:40
 * @since JDK1.8
 */
public class ParameterException extends HttpException {
    private static final long serialVersionUID = 2078393748938417226L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public ParameterException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
    }
}