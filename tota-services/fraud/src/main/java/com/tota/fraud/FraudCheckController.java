package com.tota.fraud;

import org.springframework.web.bind.annotation.*;

import com.tota.clients.fraud.FraudCheckResponse;

@RestController
@RequestMapping("api/v1/fraud")
public record FraudCheckController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

}
