package com.conf;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


//@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    Boolean swaggerEnabled = true;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
//                .enable(swaggerEnabled)
//                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build()
//                .pathMapping("/")
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XX精准功能接口")
                .description("Api文档")
                .termsOfServiceUrl("null")
                .version("1.0.0")
                .build();
    }
}