package com.solubris.air.api.human.order;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrdersResource {
    private final OrderRepository repository;

    public OrdersResource(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Mono<Order> get(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    public Mono<Order> create(Order order) {
        return repository.save(order);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return repository.deleteById(id);
    }
}