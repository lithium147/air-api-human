package com.solubris.air.api.human;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ShopItemCategories rest api doesn't feel right.
 * A ShopItemCategory is a many-to-many join object, so no need for a rest api
 * either:
 * - add/remove a shopItem to a category
 * - add/remove a category to a shopItem
 */
@RestController
@RequestMapping("/shopItemCategories")
public class ShopItemCategoriesResource {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public ShopItemCategoriesResource(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public Mono<ShopItemCategory> get(@PathVariable int id) {
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