package com.micropos.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.micropos.*", "com.micropos.repository"})
@EntityScan(basePackages = {"com.micropos.model"})
@EnableDiscoveryClient
@EnableJpaRepositories
public class CounterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class);
    }
}
