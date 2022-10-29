package com.ninespace.sqlite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableScheduling
@MapperScan("com.ninespace.sqlite.mapper")
@EnableCaching
@SpringBootApplication
@EnableSwagger2WebMvc
public class SpringDataSqliteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataSqliteApplication.class, args);
    }

}
