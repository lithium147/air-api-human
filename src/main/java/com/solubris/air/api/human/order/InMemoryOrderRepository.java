package com.solubris.air.api.human.order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderRepository implements OrderRepository {
    private final List<Order> data = new ArrayList<>();

    @Override
    public Mono<Order> findById(int id) {
        return Flux.fromIterable(data)
                .filter(o -> o.id() == id)
                .singleOrEmpty();
    }

    @Override
    public Flux<Order> findByCustomerId(int customerId) {
        return Flux.fromIterable(data)
                .filter(o -> o.customerId() == customerId);
    }

    @Override
    public Mono<Void> deleteById(int id) {
        data.removeIf(o -> o.id() == id);
        return Mono.empty().then();
    }

    @Override
    public Mono<Order> save(Order order) {
        data.add(order);
        return Mono.just(order);
    }
}