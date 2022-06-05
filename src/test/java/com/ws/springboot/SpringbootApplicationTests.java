package com.ws.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootApplicationTests {

    public static void main(String[] args) {SpringApplication.run(SpringApplication.class,args);}

    @GetMapping("/")
    public String index(){
        return "OK";
    }
}
