package cn.xilikeli.staging.module.log;

/**
 * <p>
 * access log 的 key 常量类
 * 参考自 lin-cms-springboot: <a>https://github.com/TaleLin/lin-cms-spring-boot</a>
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public class MdcAccessConstant {

    public static final String REQUEST_METHOD_MDC_KEY = "req.method";

    public static final String RESPONSE_STATUS_MDC_KEY = "res.status";

    public static final String REQUEST_REFERER_MDC_KEY = "req.referer";

    public static final String REQUEST_PROTOCOL_MDC_KEY = "req.protocol";

    public static final String REQUEST_USER_AGENT_MDC_KEY = "req.userAgent";

    public static final String REQUEST_REMOTE_HOST_MDC_KEY = "req.remoteHost";

    public static final String REQUEST_REMOTE_ADDR_MDC_KEY = "req.remoteAddr";

    public static final String REQUEST_REQUEST_URI_MDC_KEY = "req.requestURI";

    public static final String REQUEST_REQUEST_URL_MDC_KEY = "req.requestURL";

    public static final String REQUEST_QUERY_STRING_MDC_KEY = "req.queryString";

    public static final String REQUEST_X_FORWARDED_FOR_MDC_KEY = "req.xForwardedFor";

    public static final String REQUEST_BODY_BYTES_SENT_MDC_KEY = "req.bodyBytesSent";

    public static final String REQUEST_REMOTE_PORT_MDC_KEY = "req.remotePort";

}