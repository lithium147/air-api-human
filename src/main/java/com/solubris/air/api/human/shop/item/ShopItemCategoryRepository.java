package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShopItemCategoryRepository {
    Flux<ShopItemCategory> findByShopItemId(int id);
    Mono<Void> deleteByShopItemId(int id);
    Mono<Void> deleteByShopItemIdAndCategoryId(int id, int categoryId);
    Mono<Void> save(ShopItemCategory sc);
}