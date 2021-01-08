package cn.xilikeli.staging.common.configuration;

import cn.xilikeli.staging.common.log.MDCAccessServletFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <p>
 * 公用配置类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 02:05
 * @since JDK1.8
 */
@Configuration(proxyBeanMethods = false)
public class CommonConfiguration {

    /**
     * 允许的跨域域名
     */
    @Value("${staging.cors-allowed-origin}")
    private String corsAllowedOrigin;

    /**
     * 用于将 request 相关信息（如请求 url）放入 MDC 中供日志使用
     * 参考自 lin-cms-springboot: <a>https://github.com/TaleLin/lin-cms-spring-boot</a>
     *
     * @return Logback 的 MDCInsertingServletFilter
     */
    @Bean
    public FilterRegistrationBean<MDCAccessServletFilter> mdcInsertingServletFilter() {
        FilterRegistrationBean<MDCAccessServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        MDCAccessServletFilter mdcAccessServletFilter = new MDCAccessServletFilter();
        filterRegistrationBean.setFilter(mdcAccessServletFilter);
        filterRegistrationBean.setName("mdc-access-servlet-filter");
        return filterRegistrationBean;
    }

    /**
     * 自定义跨域配置
     *
     * @return 返回自定义的跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        // 跨域配置
        CorsConfiguration corsConfig = new CorsConfiguration();
        // 允许的跨域域名
        corsConfig.addAllowedOrigin(corsAllowedOrigin);
        // 设置是否支持 cookie 信息
        corsConfig.setAllowCredentials(true);
        // 设置允许的请求方式
        corsConfig.addAllowedMethod("*");
        // 设置允许的 header
        corsConfig.addAllowedHeader("*");

        // 设置路由映射路径
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration("/**", corsConfig);

        // 返回跨域配置
        return new CorsFilter(corsConfigSource);
    }

}