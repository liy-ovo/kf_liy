package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.swing.*;

/**
 * 入口类
 *
 */
@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class Application {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class,args);
    }
}
