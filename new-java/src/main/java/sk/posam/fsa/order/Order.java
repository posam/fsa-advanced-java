package sk.posam.fsa.order;

import java.math.BigDecimal;

public record Order(
        OrderStatus status,
        BigDecimal price
) {
}
