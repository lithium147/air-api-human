package com.solubris.air.api.human;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomersResource {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomersResource(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public Mono<Customer> get(@PathVariable int id) {
        return customerRepository.findById(id);
    }

    @GetMapping("/{id}/orders")
    public Flux<Order> getOrders(@PathVariable int id) {
        return this.customerRepository.findById(id)
                .map(Customer::id)
                .flatMapMany(orderRepository::findByCustomerId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return customerRepository.deleteById(id);
    }
}