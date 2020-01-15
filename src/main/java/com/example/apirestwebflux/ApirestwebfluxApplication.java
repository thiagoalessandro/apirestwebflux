package com.example.apirestwebflux;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class ApirestwebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApirestwebfluxApplication.class, args);
        log.info("funcionando 5");
    }

}
