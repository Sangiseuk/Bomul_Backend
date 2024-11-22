package com.example.bomul_backend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.example.bomul_backend.operator.model.Dao")
@Configuration
public class MyBatisConfig {

}
