package cn.xilikeli.staging.common.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Swagger2 配置类
 * 本地访问地址:
 * 官方 UI 访问地址: <a>http://localhost:端口号/swagger-ui.html</a>
 * Bootstrap UI 访问地址: <a>http://localhost:端口号/doc.html</a>
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 00:53
 * @since JDK1.8
 */
@Getter
@Setter
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
public class Swagger2Configuration {

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
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalOperation());
    }

    /**
     * 配置全局参数
     *
     * @return 返回配置的全局参数
     */
    private List<Parameter> globalOperation() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();

        // 配置 token 参数, 非必传
        tokenPar.name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .scalarExample("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjM0LCJzY29wZSI6OCwiZXhwIjoxNjc1MDU2NzcyLCJpYXQiOjE1ODg2NTY3NzJ9.FEAYgvHOOkk_x2l4od0bc5RitaXT4xaSUxaL65NT0-w(登录接口获得的令牌信息, 与最前面的 Bearer 要相隔一个空格)")
                .build();

        pars.add(tokenPar.build());

        return pars;
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