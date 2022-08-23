package com.example.dume.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author dume
 * @create 2021-12-14 16:27
 **/
@Configuration
@MapperScan(basePackages = "com.example.dume.dataserver1.dao",sqlSessionTemplateRef="SqlSessionTemplate1")
public class DataSource1Config {


    /**
     * 创建session工厂
     * @param dataSource1
     * @return
     * @throws Exception
     */
    @Primary
    @DependsOn({ "db1"}) //解决数据库循环依赖问题
    @Bean(name="SqlSessionFactory1")
    public SqlSessionFactory SqlSessionFactory1(@Qualifier("db1") DataSource dataSource1, DataSource1Config dataSource1Config) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource1);

        //加载springboot虚拟文件，防止jar包启动时找不到类
        sessionFactoryBean.setVfs(SpringBootVFS.class);
        // 设置mybatis的xml所在位置
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper1/*.xml"));
        sessionFactoryBean.setTypeAliasesPackage("com.example.dume.dataserver1.model");
        return sessionFactoryBean.getObject();

    }

    /**
     * 创建SqlSession订单模板
     * @param sessionFactory
     * @return
     */
    @Primary
    @Bean(name="SqlSessionTemplate1")
    public SqlSessionTemplate SqlSessionTemplate1(@Qualifier("SqlSessionFactory1") SqlSessionFactory sessionFactory){
        return new SqlSessionTemplate(sessionFactory);

    }
    /**
     * 主数据源事务
     */
    @Primary
    @Bean("TransactionManager1")
    @DependsOn({ "db1"})
    public PlatformTransactionManager platformTransactionManager(@Qualifier("db1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    }

