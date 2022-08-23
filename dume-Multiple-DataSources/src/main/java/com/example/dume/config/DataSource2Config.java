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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author dume
 * @create 2021-12-14 16:27
 **/
@Configuration
@MapperScan(basePackages = "com.example.dume.dataserver2.dao",sqlSessionTemplateRef="SqlSessionTemplate2")
public class DataSource2Config {


    /**
     * 创建session工厂
     * @param dataSource2
     * @return
     * @throws Exception
     * //解决数据库循环依赖问题
     */
    @DependsOn({ "db2"})
    @Bean(name="SqlSessionFactory2")
    public SqlSessionFactory SqlSessionFactory2(@Qualifier("db2") DataSource dataSource2) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource2);
        //加载springboot虚拟文件，防止jar包启动时找不到类
        sessionFactoryBean.setVfs(SpringBootVFS.class);
        // 设置mybatis的xml所在位置
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
        sessionFactoryBean.setTypeAliasesPackage("com.example.dume.dataserver2.model");
        return sessionFactoryBean.getObject();

    }
    /**
     * 创建SqlSession订单模板
     * @param sessionFactory
     * @return
     */
    @Bean(name="SqlSessionTemplate2")
    public SqlSessionTemplate SqlSessionTemplate2(@Qualifier("SqlSessionFactory2") SqlSessionFactory sessionFactory){
        return new SqlSessionTemplate(sessionFactory);

    }
    /**
     * 从数据源事务
     */
    @Bean("TransactionManager2")
    @DependsOn({ "db2"})
    public PlatformTransactionManager platformTransactionManager(@Qualifier("db2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
