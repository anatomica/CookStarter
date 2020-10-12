package ru.anatomica.cookstarter.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anatomica.cookstarter.entities.Restaurant;
import ru.anatomica.cookstarter.exceptions.ProductNotFoundException;
import ru.anatomica.cookstarter.services.RestaurantsService;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/restaurants")
@Api("Set of endpoints for CRUD operations for Restaurants")
public class RestRestaurantsController {
    private RestaurantsService restaurantsService;

    @Autowired
    public RestRestaurantsController(RestaurantsService restaurantsService) {
        this.restaurantsService = restaurantsService;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation("Returns list of all Restaurants")
    public List<Restaurant> getListRestaurants(@RequestParam(required = false) Map<String, String> requestParams){
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        return restaurantsService.findAllRestaurants();
    }

    @GetMapping(value = "/restaurant/{id}", produces = "application/json")
    @ApiOperation("Returns one Restaurant")
    public ResponseEntity<?> getOneProduct(@PathVariable @ApiParam("Id of the product to be requested. Cannot be empty") Long id) {
        if (!restaurantsService.existsById(id)) {
            throw new ProductNotFoundException("Product not found, id: " + id);
        }
        return new ResponseEntity<>(restaurantsService.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new Restaurant")
    public Restaurant saveNewProduct(@RequestBody Restaurant restaurant) {
        if (restaurant.getId() != null) {
            restaurant.setId(null);
        }
        return restaurantsService.saveOrUpdate(restaurant);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Modifies an existing Restaurant")
    public ResponseEntity<?> modifyProduct(@RequestBody Restaurant restaurant) {
        if (restaurant.getId() == null || !restaurantsService.existsById(restaurant.getId())) {
            throw new ProductNotFoundException("Product not found, id: " + restaurant.getId());
        }
        return new ResponseEntity<>(restaurantsService.saveOrUpdate(restaurant), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Removes one Restaurant by id")
    public void deleteOne(@PathVariable Long id) {
        restaurantsService.deleteById(id);
    }

}