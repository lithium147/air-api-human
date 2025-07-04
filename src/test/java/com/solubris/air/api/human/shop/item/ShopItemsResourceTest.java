package com.solubris.air.api.human.shop.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ShopItemsResourceTest {
    private static final ShopItem TEST_SHOP_ITEM = new ShopItem(1, "Test Item", 99.99);

    private final WebTestClient webTestClient;
    private final ShopItemRepository shopItemRepository;

    ShopItemsResourceTest(@Autowired WebTestClient webTestClient, @Autowired ShopItemRepository shopItemRepository) {
        this.webTestClient = webTestClient;
        this.shopItemRepository = shopItemRepository;
    }

    @BeforeEach
    void setUp() {
        shopItemRepository.save(TEST_SHOP_ITEM).block();
    }

    @Nested
    class GetShopItemById {
        @Test
        void notFound() {
            // when
            WebTestClient.ResponseSpec response = webTestClient.get()
                    .uri("/shop-items/999")
                    .exchange();

            // then
            response.expectStatus().isNotFound();
        }

        @Test
        void found() {
            // when
            WebTestClient.ResponseSpec response = webTestClient.get()
                    .uri("/shop-items/1")
                    .exchange();

            // then
            response.expectStatus().isOk()
                    .expectBody(ShopItem.class)
                    .isEqualTo(TEST_SHOP_ITEM);
        }
    }

    @Nested
    class DeleteShopItemById {
        @Test
        void notFound() {
            // when
            WebTestClient.ResponseSpec response = webTestClient.delete()
                    .uri("/shop-items/999")
                    .exchange();

            // then
            response.expectStatus().isNotFound();
        }

        @Test
        void success() {
            // when
            WebTestClient.ResponseSpec response = webTestClient.delete()
                    .uri("/shop-items/1")
                    .exchange();

            // then
            response.expectStatus().isOk();
        }
    }
}