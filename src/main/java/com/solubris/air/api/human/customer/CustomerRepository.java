package com.solubris.air.api.human.customer;

import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<Customer> findById(int id);
    Mono<Void> deleteById(int id);
    Mono<Customer> save(Customer customer);
}