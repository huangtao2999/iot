package com.dsw.iot.handler;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 达梦数据库sqlSessionFactory
 *
 * @author huangt
 * @create 2018-02-09 9:45
 **/
@Configuration
//@MapperScan(basePackages = {"com.dsw.iot.dal"}, sqlSessionFactoryRef = "dmSqlSessionFactoryBean")
public class DmDbAConfig {
    @Autowired(required = false)
    @Qualifier("dmDataSource")
    private DataSource dmDataSource;

    @Bean
    public SqlSessionFactoryBean dmSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dmDataSource);
        return factoryBean;
    }
}
