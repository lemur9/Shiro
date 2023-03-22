package com.lemur.shiro;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lemur.shiro.mapper")
@Slf4j
public class ShiroActualCombatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroActualCombatApplication.class, args);
        System.out.println("http://localhost:8087/sys/login");
    }
}
