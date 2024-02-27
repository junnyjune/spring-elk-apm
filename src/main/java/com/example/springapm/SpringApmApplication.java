package com.example.springapm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.springapm")
public class SpringApmApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(SpringApmApplication.class, args);
  }
}
