package cn.xilikeli.staging.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 请求工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 14:32
 * @since JDK1.8
 */
public class RequestUtil {

    /**
     * 获得当前请求
     *
     * @return 返回当前请求 Request 对象, 如果没有绑定会返回 null
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 当前线程没有绑定 Request
        if (requestAttributes == null) {
            return null;
        }

        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        } else {
            return null;
        }
    }

    /**
     * 获取当前请求的 url
     *
     * @return 返回当前请求的 url, 如果当前请求为 null 则返回的 url 为 null
     */
    public static String getRequestUrl() {
        HttpServletRequest request = RequestUtil.getRequest();
        if (request == null) {
            return null;
        }
        return request.getServletPath();
    }

    /**
     * 获取指定请求的简略信息
     *
     * @param request 指定的请求
     * @return 返回指定请求的简略信息
     */
    public static String getSimpleRequest(HttpServletRequest request) {
        return request.getMethod() + " " + request.getServletPath();
    }

    /**
     * 获得当前请求的简略信息
     *
     * @return 返回当前请求的简略信息, 如果当前请求为 null 则返回的简略信息为 null
     */
    public static String getSimpleRequest() {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        return request.getMethod() + " " + request.getServletPath();
    }

}