package com.cooldatasoft.rest.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring configuration via annotations
 * @author Fatih
 *
 */

//<import resource="classpath:META-INF/cxf/cxf.xml"/>
//        <import resource="classpath:META-INF/cxf/cxf-extension-xml.xml"/>
//        <import resource="classpath:META-INF/cxf/cxf-servlet.xml"/>


@Configuration
@ComponentScan("com.cooldatasoft.rest")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean( destroyMethod = "shutdown" )
    public SpringBus cxf() {
        return new SpringBus();
        }

}
