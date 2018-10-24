package com.rabbitmq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RabbitMqServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(RabbitMqServerApplication.class,args);
    }






}
