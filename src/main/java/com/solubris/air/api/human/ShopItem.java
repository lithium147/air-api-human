package com.solubris.air.api.human;

import java.util.List;

public record ShopItem(int id, String title, String description, float price, List<ShopItemCategory> categories) {
}
