package com.solubris.air.api.human.shop.item;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
            shopItemRepository.save(TEST_SHOP_ITEM).block();

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
    class CreateShopItem {
        @Test
        void success() {
            // given
            ShopItem newItem = new ShopItem(2, "New Item", 149.99);

            // when
            WebTestClient.ResponseSpec response = webTestClient.post()
                    .uri("/shop-items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(newItem)
                    .exchange();

            // then
            response.expectStatus().isCreated()
                    .expectBody(ShopItem.class)
                    .isEqualTo(newItem);
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
            shopItemRepository.save(TEST_SHOP_ITEM).block();

            // when
            WebTestClient.ResponseSpec response = webTestClient.delete()
                    .uri("/shop-items/1")
                    .exchange();

            // then
            response.expectStatus().isOk();
        }
    }

    @Nested
    class UpdateShopItem {
        @Test
        void notFound() {
            // given
            ShopItem updatedItem = new ShopItem(999, "Updated Item", 199.99);

            // when
            WebTestClient.ResponseSpec response = webTestClient.put()
                    .uri("/shop-items/999")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updatedItem)
                    .exchange();

            // then
            response.expectStatus().isNotFound();
        }

        @Test
        void success() {
            // given
            shopItemRepository.save(TEST_SHOP_ITEM).block();
            ShopItem updatedItem = new ShopItem(1, "Updated Item", 199.99);

            // when
            WebTestClient.ResponseSpec response = webTestClient.put()
                    .uri("/shop-items/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updatedItem)
                    .exchange();

            // then
            response.expectStatus().isOk()
                    .expectBody(ShopItem.class)
                    .isEqualTo(updatedItem);
        }
    }
}