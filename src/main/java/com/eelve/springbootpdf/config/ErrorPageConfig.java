package com.eelve.springbootpdf.config;



import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @ClassName ErrorPageConfig
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/6/29 20:40
 * @Version 1.0
 **/

/**
 *     //在Springboot 2之前的时候时候使用EmbeddedServletContainerCustomizer来自定义容器
 *     @Bean
 *     public EmbeddedServletContainerCustomizer containerCustomizer() {
 *         return new EmbeddedServletContainerCustomizer() {
 *             public void customize(ConfigurableEmbeddedServletContainer container) {
 *                 ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/static/error.html");
 *                 ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/static/error.html");
 *                 ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/error.html");
 *                 ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/static/error.html");
 *
 *                 container.addErrorPages(error400Page, error401Page, error404Page, error500Page);
 *             }
 *         };
 *     }
 */
@Configuration
public class ErrorPageConfig {

    /**
     * 由于在Springboot 2的时候弃用了EmbeddedServletContainerCustomizer
     * 所以在Springboot 2 版本之后使用WebServerFactoryCustomizer来自定义容器
     * @return
     */

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                    ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/static/error.html");
                    ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/static/error.html");
                    ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/error.html");
                    ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/static/error.html");
                factory.addErrorPages(error400Page,error401Page,error404Page,error500Page);
            }
        };
    }
}
