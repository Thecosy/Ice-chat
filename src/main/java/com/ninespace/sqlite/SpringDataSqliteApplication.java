package com.ninespace.sqlite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.ninespace.sqlite.mapper")
@EnableCaching
@SpringBootApplication
public class SpringDataSqliteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataSqliteApplication.class, args);
    }

}
