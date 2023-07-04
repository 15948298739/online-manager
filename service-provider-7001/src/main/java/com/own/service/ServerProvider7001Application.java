package com.own.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServerProvider7001Application {

    public static void main(String[] args) {
        SpringApplication.run(ServerProvider7001Application.class, args);
    }

}
