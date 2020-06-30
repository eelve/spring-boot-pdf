package com.eelve.springbootpdf.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @ClassName PortalTomcatWebServerCustomizer
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/6/30 14:52
 * @Version 1.0
 **/
@Component
public class PortalTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {

        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "{}[]|"));

    }

}
