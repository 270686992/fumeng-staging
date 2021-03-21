package cn.xilikeli.staging.common.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * <p>
 * Knife4j 配置
 * 本地访问地址: <a>http://localhost:端口号/doc.html</a>
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Getter
@Setter
@Configuration
@EnableSwagger2WebMvc
@ConfigurationProperties(prefix = "knife4j")
@ConditionalOnProperty(value = "knife4j.open", havingValue = "true")
public class Knife4jConfiguration {

    /**
     * API 接口包路径
     */
    private String basePackage;

    /**
     * API 页面标题
     */
    private String title;

    /**
     * API 页面描述
     */
    private String description;

    /**
     * API 服务条款地址
     */
    private String termsOfServiceUrl;

    /**
     * API 版本号
     */
    private String version;

    /**
     * 作者(联系人)名称
     */
    private String name;

    /**
     * 作者(联系人)个人站点
     */
    private String url;

    /**
     * 作者(联系人)联系邮箱
     */
    private String email;

    /**
     * 配置 Swagger2 核心配置 Docket
     *
     * @return 返回配置好的 Docket
     */
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("knife4j-2.0.8")
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 定义 API 文档汇总信息
     *
     * @return 返回定义的 API 文档汇总信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(name, url, email))
                .description(description)
                .version(version)
                .termsOfServiceUrl(termsOfServiceUrl)
                .build();
    }

}