package com.solubris.air.api.human;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShopItemCategoryRepository {
    Flux<ShopItemCategory> findByShopItemId(int id);
    Mono<Void> deleteByShopItemId(int id);
}
