package com.solubris.air.api.human.customer;

import reactor.core.publisher.Mono;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<Customer> getById(int id) {
        return repository.findById(id);
    }

    public Mono<Void> delete(int id) {
        return repository.deleteById(id);
    }

    public Mono<Customer> save(Customer customer) {
        return repository.save(customer);
    }
}