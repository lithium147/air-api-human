package com.solubris.air.api.human.customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final List<Customer> data = new ArrayList<>();

    @Override
    public Mono<Customer> findById(int id) {
        return Flux.fromIterable(data)
                .filter(c -> c.id() == id)
                .next();
    }

    @Override
    public Mono<Void> deleteById(int id) {
        data.removeIf(c -> c.id() == id);
        return Mono.empty().then();
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        data.add(customer);
        return Mono.just(customer);
    }
}