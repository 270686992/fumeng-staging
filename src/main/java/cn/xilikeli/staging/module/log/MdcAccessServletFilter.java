package cn.xilikeli.staging.module.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 记录 access log 的过滤器
 * 会把 request、response 相关信息（如 IP、URL）放入 Logback 的 MDC中
 * 参考自 lin-cms-springboot: <a>https://github.com/TaleLin/lin-cms-spring-boot</a>
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 02:00
 * @since JDK1.8
 */
public class MdcAccessServletFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MdcAccessServletFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain
            chain) throws IOException, ServletException {
        putRequestMdc(request);
        try {
            chain.doFilter(request, response);
            putResponseMdc(response);
            accessLog();
        } finally {
            clearMdc();
        }
    }

    /**
     * 记录 access log
     */
    public void accessLog() {
        log.info("");
    }

    public void putResponseMdc(ServletResponse servletResponse) {
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            MDC.put(MdcAccessConstant.RESPONSE_STATUS_MDC_KEY, String.valueOf(httpServletResponse.getStatus()));
        }
    }

    /**
     * 放入 request 信息
     */
    public void putRequestMdc(ServletRequest servletRequest) throws IOException {
        MDC.put(MdcAccessConstant.REQUEST_REMOTE_HOST_MDC_KEY, servletRequest.getRemoteHost());
        MDC.put(MdcAccessConstant.REQUEST_REMOTE_ADDR_MDC_KEY, servletRequest.getRemoteAddr());
        MDC.put(MdcAccessConstant.REQUEST_PROTOCOL_MDC_KEY, servletRequest.getProtocol());
        MDC.put(MdcAccessConstant.REQUEST_PROTOCOL_MDC_KEY, servletRequest.getProtocol());
        MDC.put(MdcAccessConstant.REQUEST_REMOTE_PORT_MDC_KEY, String.valueOf(servletRequest.getRemotePort()));
        MDC.put(MdcAccessConstant.REQUEST_BODY_BYTES_SENT_MDC_KEY, String.valueOf(servletRequest.getContentLength()));

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            MDC.put(MdcAccessConstant.REQUEST_REQUEST_URI_MDC_KEY, httpServletRequest.getRequestURI());
            StringBuffer requestUrl = httpServletRequest.getRequestURL();
            if (requestUrl != null) {
                MDC.put(MdcAccessConstant.REQUEST_REQUEST_URL_MDC_KEY, requestUrl.toString());
            }
            MDC.put(MdcAccessConstant.REQUEST_METHOD_MDC_KEY, httpServletRequest.getMethod());
            MDC.put(MdcAccessConstant.REQUEST_QUERY_STRING_MDC_KEY, httpServletRequest.getQueryString());
            MDC.put(MdcAccessConstant.REQUEST_USER_AGENT_MDC_KEY, httpServletRequest.getHeader("User-Agent"));
            MDC.put(MdcAccessConstant.REQUEST_X_FORWARDED_FOR_MDC_KEY, httpServletRequest.getHeader("X-Forwarded-For"));
            MDC.put(MdcAccessConstant.REQUEST_REFERER_MDC_KEY, httpServletRequest.getHeader("referer"));
        }
    }

    /**
     * 清理 MDC，特别重要
     * 因为 MDC 由 ThreadLocal 实现，如果不清理，线程池的复用机制会导致 MDC 数据受到污染
     */
    public void clearMdc() {
        MDC.remove(MdcAccessConstant.REQUEST_METHOD_MDC_KEY);
        MDC.remove(MdcAccessConstant.RESPONSE_STATUS_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_REFERER_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_PROTOCOL_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_USER_AGENT_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_REMOTE_HOST_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_REMOTE_ADDR_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_REQUEST_URI_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_REQUEST_URL_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_QUERY_STRING_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_X_FORWARDED_FOR_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_BODY_BYTES_SENT_MDC_KEY);
        MDC.remove(MdcAccessConstant.REQUEST_REMOTE_PORT_MDC_KEY);
    }

}