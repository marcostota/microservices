package com.tota.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckRepository fraudCheckRepository) {

    public boolean isFraudulentCustomer(Integer customerID){
        fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerID)
                        .isFraud(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return false;
    }

}
