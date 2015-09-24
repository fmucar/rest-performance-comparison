package com.cooldatasoft.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring configuration via annotations
 * @author Fatih
 *
 */

@EnableWebMvc
@Configuration
@ComponentScan("com.cooldatasoft.rest")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
