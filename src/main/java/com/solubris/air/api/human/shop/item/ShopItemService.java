package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Mono;

public class ShopItemService {
    private final ShopItemRepository repository;
    private final ShopItemCategoryRepository categoryRepository;

    public ShopItemService(ShopItemRepository repository, ShopItemCategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public Mono<ShopItem> getById(int id) {
        return repository.findById(id);
    }

    public Mono<Void> delete(int id) {
        return categoryRepository.deleteByShopItemId(id)
                .then(repository.deleteById(id));
    }
}