package com.solubris.air.api.human.shop.item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ShopItemService {
    private final ShopItemRepository repository;
    private final ShopItemCategoryRepository shopItemCategoryRepository;
    private final CategoryRepository categoryRepository;

    public ShopItemService(ShopItemRepository repository, ShopItemCategoryRepository shopItemCategoryRepository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.shopItemCategoryRepository = shopItemCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public Mono<ShopItem> getById(int id) {
        return repository.findById(id);
    }

    public Mono<ShopItem> update(int id, ShopItem shopItem) {
        return repository.findById(id)
                .flatMap(existingItem -> repository.save(shopItem));
    }

    public Mono<ShopItem> save(ShopItem shopItem) {
        return repository.save(shopItem);
    }

    /**
     * Save a call to the shopItemCategoryRepository when repository.deleteById() returns false
     */
    public Mono<Boolean> delete(int id) {
        return repository.deleteById(id)
                .doOnNext(b -> {
                    if (b) shopItemCategoryRepository.deleteByShopItemId(id);
                });
    }

    public Flux<Category> getCategories(int id) {
        return shopItemCategoryRepository.findByShopItemId(id)
                .map(ShopItemCategory::categoryId)
                .flatMap(categoryRepository::findById);
    }

    public Mono<Void> removeFromCategory(int id, String name) {
        // find the id of the category
        // remove by both shopItemId and categoryId
        return categoryRepository.findByName(name)
                .map(Category::id)
                .flatMap(categoryId -> shopItemCategoryRepository.deleteByShopItemIdAndCategoryId(id, categoryId));
    }

    public Mono<Void> addToCategory(int id, String name) {
        return categoryRepository.findByName(name)
                .switchIfEmpty(createCategoryWhenMissing(id, name))
                .map(Category::id)
                .map(categoryId -> new ShopItemCategory(id, categoryId))
                .flatMap(shopItemCategoryRepository::save);
    }

    private Mono<Category> createCategoryWhenMissing(int id, String name) {
        return Mono.just(new Category(id, name, ""))
                .flatMap(categoryRepository::save);
    }
}