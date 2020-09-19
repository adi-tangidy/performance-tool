package com.abb.es.df.perf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PerformanceToolApplication {

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(PerformanceToolApplication.class, args)));
    }

}
