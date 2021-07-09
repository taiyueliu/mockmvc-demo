package com.example.mockmvcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MockmvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockmvcDemoApplication.class, args);
    }
    @GetMapping("/test")
    public String getTest(String ss){
        return ss;
    }

}
