package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LMM
 * @date 11:06 2021/4/23
 * @desc
 */
@SpringBootApplication
@EnableSwagger2
public class ExapleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExapleApplication.class,args);
    }
}
