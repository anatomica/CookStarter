package ru.anatomica.cookstarter.entities.dtos;

import java.math.BigDecimal;

public interface ProductDto {
    Long getId();
    String getTitle();
    void setTitle(String title);
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
}
