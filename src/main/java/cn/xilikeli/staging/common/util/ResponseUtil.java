package cn.xilikeli.staging.common.util;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.vo.response.UnifyResponseVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 统一 API 响应结果生成工具类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@SuppressWarnings("all")
public class ResponseUtil {

    /**
     * 获得当前响应
     *
     * @return 返回当前响应
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 设置当前响应的 http 状态码
     *
     * @param httpStatus 设置的 http 状态码
     */
    public static void setCurrentResponseHttpStatus(int httpStatus) {
        getResponse().setStatus(httpStatus);
    }

    /**
     * 生成统一 API 响应结果
     *
     * @return 返回生成的统一 API 响应结果
     */
    public static <T> UnifyResponseVO<T> generateUnifyResponseVO() {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .code(CodeEnum.SUCCESS.getCode())
                .message(CodeEnum.SUCCESS.getDescription())
                .success(CodeEnum.SUCCESS.getSuccess())
                .data(null)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    /**
     * 生成统一 API 响应结果
     *
     * @param data 响应数据
     * @return 返回生成的统一 API 响应结果
     */
    public static <T> UnifyResponseVO<T> generateUnifyResponseVO(T data) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .code(CodeEnum.SUCCESS.getCode())
                .message(CodeEnum.SUCCESS.getDescription())
                .success(CodeEnum.SUCCESS.getSuccess())
                .data(data)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    /**
     * 生成统一 API 响应结果
     *
     * @param code    消息码
     * @param success 是否成功
     * @return 返回生成的统一 API 响应结果
     */
    public static <T> UnifyResponseVO<T> generateUnifyResponseVO(Integer code, Boolean success) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .code(code)
                .message(null)
                .success(success)
                .data(null)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    /**
     * 生成统一 API 响应结果
     *
     * @param code    消息码
     * @param message 响应信息
     * @param success 是否成功
     * @return 返回生成的统一 API 响应结果
     */
    public static <T> UnifyResponseVO<T> generateUnifyResponseVO(Integer code, String message, Boolean success) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .code(code)
                .message(message)
                .success(success)
                .data(null)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    /**
     * 生成统一 API 响应结果
     *
     * @param code    消息码
     * @param data    响应数据
     * @param success 是否成功
     * @return 返回生成的统一 API 响应结果
     */
    public static <T> UnifyResponseVO<T> generateUnifyResponseVO(Integer code, T data, Boolean success) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .code(code)
                .message(null)
                .success(success)
                .data(data)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    /**
     * 生成统一 API 响应结果
     *
     * @param code    消息码
     * @param message 响应信息
     * @param success 是否成功
     * @param data    响应数据
     * @return 返回生成的统一 API 响应结果
     */
    public static <T> UnifyResponseVO<T> generateUnifyResponseVO(Integer code, String message, Boolean success, T data) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .code(code)
                .message(message)
                .success(success)
                .data(data)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

}