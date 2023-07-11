package com.tota.notification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tota.amqp.RabbitMQMessageProducer;

@SpringBootApplication(scanBasePackages = {
        "com.tota.notification",
        "com.tota.amqp"
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    // @Bean
    // CommandLineRunner commandLineRunner(
    // RabbitMQMessageProducer producer,
    // NotificationConfig notificationConfig) {
    // return args -> {
    // producer.publish("foo",
    // notificationConfig.getInternalExchange(),
    // notificationConfig.getInternalNotificationRoutingKey());
    // };
    // }
}
