package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Mono;

public interface ShopItemRepository {
    Mono<ShopItem> findById(int id);
    Mono<Boolean> deleteById(int id);
    Mono<ShopItem> save(ShopItem shopItem);
}