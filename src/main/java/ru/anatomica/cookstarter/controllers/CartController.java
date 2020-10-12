package ru.anatomica.cookstarter.controllers;

import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.dtos.OrderItemDto;
import ru.anatomica.cookstarter.exceptions.ResourceNotFoundException;
import ru.anatomica.cookstarter.services.CartService;
import ru.anatomica.cookstarter.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.anatomica.cookstarter.services.ProductsService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private OrderItemService orderItemService;
    private ProductsService productsService;
    private CartService cartService;

    @GetMapping
    public List<OrderItemDto> getCartContent() {
        return orderItemService.mapEntityListToDtoList(cartService.getItems());
    }

    @GetMapping("/add/{productId}")
    public void addProductToCartById(@PathVariable Long productId) {
        Product product = productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to add product (id = " + productId + " ) to cart. Product not found"));
        cartService.add(product);
    }

    @GetMapping("/decrement/{productId}")
    public void decrementProductToCartById(@PathVariable Long productId) {
        cartService.decrement(productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to decrement product (id = " + productId + " ) in cart. Product not found")));
    }

    @GetMapping("/remove/{productId}")
    public void removeProductFromCartById(@PathVariable Long productId) {
        cartService.removeByProductId(productId);
    }
}