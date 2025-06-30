package com.solubris.air.api.human.customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public Mono<Customer> findById(int id) {
        return Flux.fromIterable(customers)
                .filter(c -> c.id() == id)
                .next();
    }

    @Override
    public Mono<Void> deleteById(int id) {
        customers.removeIf(c -> c.id() == id);
        return Mono.empty().then();
    }
}