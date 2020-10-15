package ru.anatomica.cookstarter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.anatomica.cookstarter.entities.Order;
import ru.anatomica.cookstarter.entities.User;
import ru.anatomica.cookstarter.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;

@Controller
@RequestMapping("/profile")
public class UserController {
    public UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showUser(Model model) {
        model.addAttribute("user", usersService);
        return "profile_page";
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(Principal principal) {
        User user = usersService.findByEmail(principal.getName()).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}