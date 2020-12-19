package cn.xilikeli.staging.extension.file;

import cn.xilikeli.staging.module.file.Uploader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * <p>
 * 文件上传配置类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 20:09
 * @since JDK1.8
 */
@Configuration
public class UploaderConfiguration {
    /**
     * 切换到七牛云文件上传实现类
     * 使用本地上传时将该方法注释即可, 同时需要注意 config.yml 配置文件中配置的文件服务域名需要为本地的
     *
     * @return 七牛云文件上传实现类
     */
//    @Bean
//    @Primary
//    public Uploader qiNiuUploader() {
//        return new QiNiuUploader();
//    }

    /**
     * 切换到本地文件上传实现类
     * 使用七牛云上传时不需要将该方法注释, 放开七牛云的方法注释即可
     *
     * @return 返回本地文件上传实现类
     */
    @Bean
    @Order
    @ConditionalOnMissingBean
    public Uploader uploader(){
        return new LocalUploader();
    }
}