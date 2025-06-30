package com.solubris.air.api.human.order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Order> findById(int id);
    Flux<Order> findByCustomerId(int customerId);
    Mono<Void> deleteById(int id);
    Mono<Order> save(Order order);
}