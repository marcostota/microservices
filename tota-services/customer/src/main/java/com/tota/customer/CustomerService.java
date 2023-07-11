package com.tota.customer;

import com.tota.amqp.RabbitMQMessageProducer;
import com.tota.clients.fraud.FraudCheckResponse;
import com.tota.clients.fraud.FraudClient;
import com.tota.clients.notification.NotificationClient;
import com.tota.clients.notification.NotificationRequest;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient,
                NotificationClient notificationClient, RabbitMQMessageProducer rabbitMQMessageProducer) {

        public void registerCustomer(CustomerRegisterRequest customerRegisterRequest) {
                Customer customer = Customer.builder()
                                .firstName(customerRegisterRequest.firstName())
                                .lastName(customerRegisterRequest.lastName())
                                .email(customerRegisterRequest.email())
                                .build();
                customerRepository.saveAndFlush(customer);

                FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

                if (fraudCheckResponse.isFraudster()) {
                        throw new IllegalStateException("fraudster");
                }

                NotificationRequest notificationRequest = new NotificationRequest(
                                customer.getId(),
                                customer.getFirstName(),
                                String.format("Hi %s, Welcome to totaworld...",
                                                customer.getFirstName()),
                                customer.getEmail());

                rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange",
                                "internal.notification.routing-key");

        }
}
