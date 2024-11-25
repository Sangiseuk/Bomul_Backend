package com.example.bomul_backend.config;
import com.example.bomul_backend.game.model.entity.Scope;
import com.example.bomul_backend.game.model.typehandler.ScopeTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@MapperScan(basePackages = {
        "com.example.bomul_backend.operator.model.dao",
        "com.example.bomul_backend.game.model.dao"
}) @Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();

        // TypeHandler 등록
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.getTypeHandlerRegistry().register(Scope.ScopeType.class, ScopeTypeHandler.class);

        return sqlSessionFactory;
    }
}