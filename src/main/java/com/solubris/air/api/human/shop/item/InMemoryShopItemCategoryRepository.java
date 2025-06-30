package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShopItemCategoryRepository implements ShopItemCategoryRepository {
    private final List<ShopItemCategory> categories = new ArrayList<>();

    @Override
    public Flux<ShopItemCategory> findByShopItemId(int id) {
        return Flux.fromIterable(categories)
                .filter(c -> c.id() == id);
    }

    @Override
    public Mono<Void> deleteByShopItemId(int id) {
        categories.removeIf(c -> c.id() == id);
        return Mono.empty().then();
    }
}