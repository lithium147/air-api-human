package com.solubris.air.api.human;

import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<Customer> findById(int id);
    Mono<Void> deleteById(int id);
}
