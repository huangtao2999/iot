package com.dsw.iot.handler;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 达梦数据库sqlSessionFactory
 *
 * @author huangt
 * @create 2018-02-09 9:45
 **/
@Configuration
@MapperScan(basePackages = {"com.dsw.iot.dmdal"}, sqlSessionFactoryRef = "dmSqlSessionFactoryBean")
public class DmDbAConfig {
    @Autowired(required = false)
    @Qualifier("dmDataSource")
    private DataSource dmDataSource;

    @Bean
    public SqlSessionFactoryBean dmSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dmDataSource);
        //TODO;子库因为和主库mapper放在一起，多数据源的时候 不会再次加载。需要手动设置路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/dsw/iot/dal/*.xml"));
        return factoryBean;
    }
}
