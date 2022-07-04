package com.micropos.order;


import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.micropos.*", "com.micropos.repository"})
@EntityScan(basePackages = {"com.micropos.model"})
@EnableDiscoveryClient
@EnableJpaRepositories
public class OrderApplication {

    private static final boolean NON_DURABLE = false;
    private static final String QUEUE_NAME = "OrderQueue";

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public Queue orderQueue() {
        return new Queue(QUEUE_NAME, NON_DURABLE);
    }

}
