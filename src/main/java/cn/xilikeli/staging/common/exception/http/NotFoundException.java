package cn.xilikeli.staging.common.exception.http;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * 找不到资源异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:39
 * @since JDK1.8
 */
public class NotFoundException extends HttpException {
    private static final long serialVersionUID = 2432756194955583275L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public NotFoundException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.NOT_FOUND.value();
    }
}