package com.solubris.air.api.human.shop.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/shop-items")
public class ShopItemsResource {
    private final ShopItemService service;

    public ShopItemsResource(ShopItemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Mono<ShopItem> get(@PathVariable int id) {
        return service.getById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ShopItem> create(@RequestBody ShopItem shopItem) {
        return service.save(shopItem);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return service.delete(id)
                .flatMap(b -> {
                    if (!b) return Mono.error(new ResponseStatusException(NOT_FOUND));
                    return Mono.empty().then();
                });
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

    @PutMapping("/{id}")
    public Mono<ShopItem> update(@PathVariable int id, @RequestBody ShopItem shopItem) {
        return service.update(id, shopItem)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}