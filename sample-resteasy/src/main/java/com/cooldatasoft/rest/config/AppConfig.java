package com.cooldatasoft.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring configuration via annotations
 * @author Fatih
 *
 */

@Configuration
@ComponentScan("com.cooldatasoft.rest")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
