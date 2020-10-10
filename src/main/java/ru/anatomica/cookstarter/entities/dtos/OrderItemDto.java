package ru.anatomica.cookstarter.entities.dtos;

import ru.anatomica.cookstarter.entities.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private BigDecimal price;
    private int quantity;
    private String productTitle;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getProduct().getId();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
        this.productTitle = orderItem.getProduct().getTitle();
    }
}
