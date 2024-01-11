package com.nuevo.springboot.reservas.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BraintreeConfig {

    @Value("${braintree.merchant-id}")
    private String merchantId;

    @Value("${braintree.public-key}")
    private String publicKey;

    @Value("${braintree.private-key}")
    private String privateKey;
    
    

    public String getMerchantId() {
		return merchantId;
	}



	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}



	public String getPublicKey() {
		return publicKey;
	}



	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}



	public String getPrivateKey() {
		return privateKey;
	}



	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}



	@Bean
    public BraintreeGateway braintreeGateway() {
        return new BraintreeGateway(
                Environment.SANDBOX, // You may need to import Environment
                merchantId,
                publicKey,
                privateKey
        );
    }
}
