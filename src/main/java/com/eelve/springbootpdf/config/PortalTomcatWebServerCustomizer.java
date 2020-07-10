package com.eelve.springbootpdf.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @ClassName PortalTomcatWebServerCustomizer
 * @Description Tomcat严格按照 RFC 3986规范进行访问解析，而 RFC 3986规范定义了Url中只允许包含英文字母（a-zA-Z）、数字（0-9）、-_.~4个特殊字符以及所有保留字符(RFC3986中指定了以下字符为保留字符：! * ’ ( ) ; : @ & = + $ , / ? # [ ])。传入的参数中有"{"不在RFC3986中的保留字段中，所以会报参数异常错
 * @Author zhao.zhilue
 * @Date 2020/6/30 14:52
 * @Version 1.0
 **/
@Component
public class PortalTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {

        /**
         * 允许"{}[]|"特殊字符，避免Tomcat异常报错
         */
        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "{}[]|"));

    }

}
