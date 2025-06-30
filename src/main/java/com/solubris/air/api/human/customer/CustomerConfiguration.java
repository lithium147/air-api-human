package com.solubris.air.api.human.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    public CustomerRepository customerRepository() {
        return new InMemoryCustomerRepository();
    }

    @Bean
    public CustomerService customerService(CustomerRepository repository) {
        return new CustomerService(repository);
    }
}