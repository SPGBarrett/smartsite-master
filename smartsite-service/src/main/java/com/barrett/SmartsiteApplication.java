package com.barrett;

import com.barrett.smartsite.config.CrossOriginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class SmartsiteApplication {

    public static void main(String[] args) { SpringApplication.run(SmartsiteApplication.class, args); }

    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.addUrlPatterns(new String[] { "/*" });
        filterRegistrationBean.setFilter(new CrossOriginFilter());
        return filterRegistrationBean;
    }
}
