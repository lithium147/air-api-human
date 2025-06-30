package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShopItemRepository implements ShopItemRepository {
    private final List<ShopItem> shopItems = new ArrayList<>();

    @Override
    public Mono<ShopItem> findById(int id) {
        return Flux.fromIterable(shopItems)
                .filter(s -> s.id() == id)
                .singleOrEmpty();
    }

    @Override
    public Mono<Void> deleteById(int id) {
        shopItems.removeIf(s -> s.id() == id);
        return Mono.empty().then();
    }
}