package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShopItemCategoryRepository implements ShopItemCategoryRepository {
    private final List<ShopItemCategory> data = new ArrayList<>();

    @Override
    public Flux<ShopItemCategory> findByShopItemId(int id) {
        return Flux.fromIterable(data)
                .filter(c -> c.shopItemId() == id);
    }

    @Override
    public Mono<Void> deleteByShopItemId(int id) {
        data.removeIf(c -> c.shopItemId() == id);
        return Mono.empty().then();
    }

    @Override
    public Mono<Void> deleteByShopItemIdAndCategoryId(int id, int categoryId) {
        data.removeIf(c -> c.shopItemId() == id && c.categoryId() == categoryId);
        return Mono.empty().then();
    }

    @Override
    public Mono<Void> save(ShopItemCategory sc) {
        return Mono.just(data.add(sc))
                .then();
    }
}