package com.dsw.iot.handler;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 物联网mysql数据库sqlSessionFactory
 *
 * @author huangt
 * @create 2018-02-09 9:45
 **/
@Configuration
@MapperScan(basePackages = {"com.dsw.iot.dal"}, sqlSessionFactoryRef = "mysqlSqlSessionFactoryBean")
public class IotDbAConfig {
    @Autowired(required = false)
    @Qualifier("mysqlDataSource")
    private DataSource mysqlDataSource;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean mysqlSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mysqlDataSource);
        return factoryBean;
    }
}
