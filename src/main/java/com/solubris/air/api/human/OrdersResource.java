package com.solubris.air.api.human;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrdersResource {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrdersResource(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    public Mono<Order> get(@PathVariable int id) {
        return orderRepository.findById(id);
    }

    @GetMapping("/{id}/customer")
    public Mono<Customer> getCustomer(@PathVariable int id) {
        return orderRepository.findById(id)
                .map(Order::customerId)
                .flatMap(customerRepository::findById);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return orderRepository.deleteById(id);
    }
}