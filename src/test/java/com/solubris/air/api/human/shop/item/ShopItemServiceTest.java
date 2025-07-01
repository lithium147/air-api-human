package com.solubris.air.api.human.shop.item;

import com.solubris.air.api.human.WithExceptionType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static pl.rzrz.assertj.reactor.Assertions.assertThat;

class ShopItemServiceTest {
    private final List<ShopItem> shopItemData = new ArrayList<>();
    private final ShopItemRepository repository = new InMemoryShopItemRepository(shopItemData);
    private final ShopItemCategoryRepository shopItemCategoryRepository = new InMemoryShopItemCategoryRepository();
    private final CategoryRepository categoryRepository = new InMemoryCategoryRepository();
    private final ShopItemService underTest = new ShopItemService(repository, shopItemCategoryRepository, categoryRepository);

    @Nested
    class GetById {
        ShopItem shopItem = new ShopItem(1, "name", 1.00);

        @Test
        void canReturnNothing() {

            Mono<ShopItem> byId = underTest.getById(-1);

            assertThat(byId)
                    .emitsExactly();
        }

        @Test
        void canReturnOneShopItem() {
            shopItemData.add(shopItem);

            Mono<ShopItem> byId = underTest.getById(1);

            assertThat(byId)
                    .emitsExactly(shopItem);
        }

        @Test
        void cannotReturnMultipleShopItems() {
            shopItemData.add(shopItem);
            shopItemData.add(shopItem);

            Mono<ShopItem> byId = underTest.getById(1);

            assertThat(byId)
                    .sendsError(WithExceptionType.of(IndexOutOfBoundsException.class));
        }
    }
}