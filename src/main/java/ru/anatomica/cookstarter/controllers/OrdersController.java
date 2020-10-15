package ru.anatomica.cookstarter.controllers;

import ru.anatomica.cookstarter.entities.Order;
import ru.anatomica.cookstarter.entities.User;
import ru.anatomica.cookstarter.services.CartService;
import ru.anatomica.cookstarter.services.OrdersService;
import ru.anatomica.cookstarter.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController {

    private UsersService usersService;
    private OrdersService ordersService;
    private CartService cartService;
    private List<Object> myOrder;

    @GetMapping()
    public ResponseEntity<?> getOrder(Principal principal) {
        User user = usersService.findByEmail(principal.getName()).get();
        Order order = new Order(user, cartService, "1", "1");
        myOrder = Collections.singletonList(order);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }

    @GetMapping("/create")
    public String createOrder(Principal principal, Model model) {
        User user = usersService.findByEmail(principal.getName()).get();
        model.addAttribute("user", user);
        return "order_info";
    }

    @PostMapping(value = "/confirm", produces = "application/json")
    public ResponseEntity<?> confirmOrder(Principal principal, @RequestParam(required = false) String address, String phone) {
        User user = usersService.findByEmail(principal.getName()).get();
        Order order = new Order(user, cartService, phone, address);
        myOrder.clear();
        cartService.clear();
        return new ResponseEntity<>(ordersService.saveOrder(order), HttpStatus.CREATED);
    }

}
