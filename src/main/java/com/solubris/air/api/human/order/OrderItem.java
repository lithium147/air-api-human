package com.solubris.air.api.human.order;

import com.solubris.air.api.human.shop.item.ShopItem;

public record OrderItem(int id, ShopItem shopItem, int quantity) {

}