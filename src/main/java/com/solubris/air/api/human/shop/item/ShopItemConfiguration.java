package com.solubris.air.api.human.shop.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopItemConfiguration {

    @Bean
    public ShopItemRepository shopItemRepository() {
        return new InMemoryShopItemRepository();
    }

    @Bean
    public ShopItemCategoryRepository shopItemCategoryRepository() {
        return new InMemoryShopItemCategoryRepository();
    }

    @Bean
    public ShopItemService shopItemService(ShopItemRepository repository, ShopItemCategoryRepository categoryRepository) {
        return new ShopItemService(repository, categoryRepository);
    }
}