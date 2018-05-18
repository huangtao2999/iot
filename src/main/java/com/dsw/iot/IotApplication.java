package com.dsw.iot;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.dsw.iot.handler.PrivilegeInterceptor;

@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IotApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }


    @Bean
    public PrivilegeInterceptor privilegeInterceptor() {
        return new PrivilegeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(privilegeInterceptor());
        interceptorRegistration.addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean("mysqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql")
    public DataSource mysqlDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dmDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.dm")
    public DataSource dmDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


}
