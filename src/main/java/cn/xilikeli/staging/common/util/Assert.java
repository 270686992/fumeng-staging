package cn.xilikeli.staging.common.util;

import cn.xilikeli.staging.common.exception.http.FailedException;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.exception.http.ParameterException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * <p>
 * 自定义断言工具类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/1/31
 * @since JDK1.8
 */
public class Assert {

    private Assert() {

    }

    public static void checkNotFoundResource(Object o, Integer code) {
        if (o == null) {
            throw new NotFoundException(code);
        }
    }

    public static void checkNotNull(Object o, Integer code) {
        if (o == null) {
            throw new ParameterException(code);
        }
    }

    public static void checkNotEmpty(Collection<?> coll, Integer code) {
        if (CollectionUtils.isEmpty(coll)) {
            throw new ParameterException(code);
        }
    }

    public static void checkArgument(boolean expression, Integer code) {
        if (expression) {
            throw new ParameterException(code);
        }
    }

    public static void checkFailed(boolean expression, Integer code) {
        if (expression) {
            throw new FailedException(code);
        }
    }

}