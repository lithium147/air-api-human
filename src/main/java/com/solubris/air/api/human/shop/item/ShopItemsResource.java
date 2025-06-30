package com.solubris.air.api.human.shop.item;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * How to deal with categories?
 * Category could be treated like tags.
 * So just add the item to a category, and if the category exists,
 * use that, otherwise create it.
 * So can have end points under shop-items/id/categories/...
 * But what about endpoint to return all categories?
 * shop-items/categories/...
 */
@RestController
@RequestMapping("/shop-items")
public class ShopItemsResource {
    private final ShopItemService service;

    public ShopItemsResource(ShopItemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Mono<ShopItem> get(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/categories")
    public Flux<Category> getCategories(@PathVariable int id) {
        return service.getCategories(id);
    }

    /**
     * What is rest to add a shopItem to a category?
     * put/post should update all the categories
     * What about patch?
     * patch is meant to update only the given fields.
     * but a list of categories doesn't have fields.
     */
    @PutMapping("/{id}/categories/{name}")
    public Mono<Void> addToCategory(@PathVariable int id, @PathVariable String name) {
        return service.addToCategory(id, name);
    }

    @DeleteMapping("/{id}/categories/{name}")
    public Mono<Void> removeFromCategory(@PathVariable int id, @PathVariable String name) {
        return service.removeFromCategory(id, name);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }
}