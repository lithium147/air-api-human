package com.solubris.air.api.human.order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderRepository implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public Mono<Order> findById(int id) {
        return Flux.fromIterable(orders)
                .filter(o -> o.id() == id)
                .singleOrEmpty();
    }

    @Override
    public Flux<Order> findByCustomerId(int customerId) {
        return Flux.fromIterable(orders)
                .filter(o -> o.customerId() == customerId);
    }

    @Override
    public Mono<Void> deleteById(int id) {
        orders.removeIf(o -> o.id() == id);
        return Mono.empty().then();
    }
}