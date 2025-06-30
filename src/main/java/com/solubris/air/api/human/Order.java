package com.solubris.air.api.human;

import java.util.List;

public record Order(int id, int customerId, List<OrderItem> items) {
}
