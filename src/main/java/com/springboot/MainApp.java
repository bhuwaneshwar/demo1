package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MainApp  extends SpringBootServletInitializer {
   public static void main(String[] args) {
      SpringApplication.run(MainApp.class, args);
   }
}
