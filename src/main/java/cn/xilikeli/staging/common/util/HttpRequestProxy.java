package cn.xilikeli.staging.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * 获取用户终端 IP 地址的工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 16:09
 * @since JDK1.8
 */
@Component
@Slf4j
public class HttpRequestProxy {

    /**
     * 用户的请求
     */
    private static HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        HttpRequestProxy.request = request;
    }

    /**
     * 获取用户请求的 url
     *
     * @return 返回用户请求的 url
     */
    public static String getRequestUrl() {
        return HttpRequestProxy.request.getRequestURI();
    }

    /**
     * 获取用户远程真实 IP 地址
     *
     * @return 返回用户远程真实 IP 地址
     */
    public static String getRemoteRealIp() {
        HttpServletRequest request = HttpRequestProxy.request;
        String ipAddress = null;

        try {
            ipAddress = request.getHeader("x-forwarded-for");

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equals(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equals(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equals(ipAddress)) {
                ipAddress = request.getRemoteAddr();

                if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                    InetAddress inetAddress = null;

                    try {
                        inetAddress = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.error("HttpRequestProxy 获取本地 IP 地址时出现异常: ", e);
                    }

                    if (inetAddress == null) {
                        ipAddress = "127.0.0.1";
                    } else {
                        ipAddress = inetAddress.getHostAddress();
                    }
                }
            }

            // 对于通过多个代理的情况，第一个 IP 为客户端真实 IP,多个 IP 按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error("HttpRequestProxy 获取 IP 地址时出现异常: ", e);
            ipAddress = "";
        }

        return ipAddress;
    }

}