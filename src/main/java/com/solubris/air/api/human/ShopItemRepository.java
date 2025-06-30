package com.solubris.air.api.human;

import reactor.core.publisher.Mono;

public interface ShopItemRepository {
    Mono<ShopItem> findById(int id);
    Mono<Void> deleteById(int id);
}
