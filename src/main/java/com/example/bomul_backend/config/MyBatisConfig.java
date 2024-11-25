package com.example.bomul_backend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = {
        "com.example.bomul_backend.operator.model.dao",
        "com.example.bomul_backend.game.model.dao"
})@Configuration
public class MyBatisConfig {

}

