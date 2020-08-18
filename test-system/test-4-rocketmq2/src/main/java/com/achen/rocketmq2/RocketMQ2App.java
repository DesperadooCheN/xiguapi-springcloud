package com.achen.rocketmq2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RocketMQ2App {
    public static void main(String[] args) {
        SpringApplication.run(RocketMQ2App.class);
    }
}
