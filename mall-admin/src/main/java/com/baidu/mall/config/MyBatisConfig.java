package com.baidu.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.baidu.mall.mapper","com.baidu.mall.dao"})
public class MyBatisConfig {
}
