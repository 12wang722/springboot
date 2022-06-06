package com.ws.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@SpringBootApplication
@EnableWebMvc
public class SpringbootApplication {

    public static void main(String[] args) {SpringApplication.run(SpringbootApplication.class,args);}

    @GetMapping("/")
    public String index(){
        return "OK";
    }
}
