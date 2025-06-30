package com.solubris.air.api.human.shop.item;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }
}