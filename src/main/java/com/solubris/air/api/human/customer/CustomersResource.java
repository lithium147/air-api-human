package com.solubris.air.api.human.customer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomersResource {
    private final CustomerService service;

    public CustomersResource(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Mono<Customer> get(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public Mono<Customer> create(Customer customer) {
        return service.save(customer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }
}