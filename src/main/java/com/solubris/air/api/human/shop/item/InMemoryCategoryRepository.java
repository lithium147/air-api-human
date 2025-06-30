package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCategoryRepository implements CategoryRepository {
    private final List<Category> data = new ArrayList<>();

    @Override
    public Mono<Category> findById(int id) {
        return Flux.fromIterable(data)
                .filter(c -> c.id() == id)
                .singleOrEmpty();
    }

    @Override
    public Mono<Category> findByName(String name) {
        return Flux.fromIterable(data)
                .filter(c -> c.name().equals(name))
                .singleOrEmpty();
    }

    @Override
    public Mono<Category> save(Category category) {
        data.add(category);
        return Mono.just(category);
    }
}