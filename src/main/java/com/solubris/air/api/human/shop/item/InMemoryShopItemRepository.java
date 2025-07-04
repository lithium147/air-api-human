package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShopItemRepository implements ShopItemRepository {
    private final List<ShopItem> data;

    public InMemoryShopItemRepository(List<ShopItem> data) {
        this.data = data;
    }

    public InMemoryShopItemRepository() {
        this(new ArrayList<>());
    }

    @Override
    public Mono<ShopItem> findById(int id) {
        return Flux.fromIterable(data)
                .filter(s -> s.id() == id)
                .singleOrEmpty();
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return Mono.just(data.removeIf(s -> s.id() == id))
                .then();
    }

    @Override
    public Mono<ShopItem> save(ShopItem shopItem) {
        return Mono.just(data.add(shopItem)).thenReturn(shopItem);
    }
}