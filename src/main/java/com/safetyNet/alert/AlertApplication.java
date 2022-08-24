package com.safetyNet.alert;

import com.safetyNet.alert.service.GenerateDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AlertApplication {


    public static void main(String[] args) {
        SpringApplication.run(AlertApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(GenerateDataService generateDataService) {

        return args -> {
            generateDataService.generateData();

        };
    }

}
