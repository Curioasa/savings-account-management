package com.ing.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Clock;

@SpringBootApplication
@EnableJpaRepositories("com.ing.technicaltest.dao")
@ConfigurationPropertiesScan("com.ing.technicaltest.props")
public class TechnicalTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalTestApplication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

}
