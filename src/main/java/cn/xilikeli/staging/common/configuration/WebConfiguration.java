package cn.xilikeli.staging.common.configuration;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * <p>
 * Spring MVC 配置
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 01:41
 * @since JDK1.8
 */
@Configuration(proxyBeanMethods = false)
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 本地文件保存位置
     */
    @Value("${staging.file.store-dir:assets/}")
    private String storeDir;

    /**
     * 服务器文件路径
     */
    @Value("${staging.file.serve-path:assets/**}")
    private String servePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 指定 knife4j 的静态资源处理
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/public");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 指定服务器图片资源处理
        registry.addResourceHandler(getDirServePath())
                .addResourceLocations("file:" + getAbsDir() + "/");
    }

    /**
     * 获得服务器文件路径
     */
    private String getDirServePath() {
        return servePath;
    }

    /**
     * 获得本地文件保存位置的路径
     */
    private String getAbsDir() {
        if (FileUtil.isAbsolutePath(storeDir)) {
            return storeDir;
        }

        String cmd = System.getProperty("user.dir");
        Path path = FileSystems.getDefault().getPath(cmd, storeDir);
        return path.toAbsolutePath().toString();
    }

}