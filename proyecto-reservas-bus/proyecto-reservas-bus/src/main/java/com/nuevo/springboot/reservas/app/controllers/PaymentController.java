package com.nuevo.springboot.reservas.app.controllers;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private BraintreeGateway braintreeGateway;

    @GetMapping("/token")
    public ResponseEntity<String> getClientToken() {
        ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
        String clientToken = braintreeGateway.clientToken().generate(clientTokenRequest);
        return ResponseEntity.ok(clientToken);
    }

    @PostMapping("/process-payment")
    public ResponseEntity<String> processPayment(@RequestParam String nonce, @RequestParam BigDecimal amount) {
        TransactionRequest request = new TransactionRequest()
                .amount(amount)
                .paymentMethodNonce(nonce)
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = braintreeGateway.transaction().sale(request);
        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            return ResponseEntity.ok("Payment successful. Transaction ID: " + transaction.getId());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment processing error: " + result.getMessage());
        }
    }
}