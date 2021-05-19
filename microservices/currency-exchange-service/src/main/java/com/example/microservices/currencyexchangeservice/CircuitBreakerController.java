package com.example.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/sampleApi")
    @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")  // Retrying for service
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse") // Breaking the circuit during service down with very frequent requests
    @RateLimiter(name = "default") //Allowing only certain amount of calls per certain period
    @Bulkhead(name = "sample-api") //Can be used to limit max concurrent calls to a API
    public String sampleApi() {
        logger.info("Sample Api request received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-api", String.class);
        return forEntity.getBody();
    }

    private String hardCodedResponse(Exception ex) {
        return "fallback-response";
    }
}
