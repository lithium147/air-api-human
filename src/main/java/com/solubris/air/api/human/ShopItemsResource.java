package com.solubris.air.api.human;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Products would be a better name.
 */
@RestController
@RequestMapping("/shopItems")
public class ShopItemsResource {
    private final ShopItemRepository shopItemRepository;
    private final ShopItemCategoryRepository shopItemCategoryRepository;

    public ShopItemsResource(ShopItemRepository shopItemRepository, ShopItemCategoryRepository shopItemCategoryRepository) {
        this.shopItemRepository = shopItemRepository;
        this.shopItemCategoryRepository = shopItemCategoryRepository;
    }

    @GetMapping("/{id}")
    public Mono<ShopItem> get(@PathVariable int id) {
        return shopItemRepository.findById(id);
    }

    @GetMapping("/{id}/categories")
    public Flux<ShopItemCategory> getCategories(@PathVariable int id) {
        return shopItemCategoryRepository.findByShopItemId(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return shopItemCategoryRepository.deleteByShopItemId(id)
                .then(shopItemRepository.deleteById(id));
    }
}