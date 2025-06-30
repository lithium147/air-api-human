package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Mono;

public interface CategoryRepository {
    Mono<Category> findById(int id);
    Mono<Category> findByName(String name);
    Mono<Category> save(Category category);
}
