package ru.anatomica.cookstarter.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.Restaurant;
import ru.anatomica.cookstarter.entities.dtos.ProductDto;
import ru.anatomica.cookstarter.exceptions.ProductNotFoundException;
import ru.anatomica.cookstarter.services.ProductsService;
import ru.anatomica.cookstarter.utils.ProductFilter;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/restaurants")
@Api("Set of endpoints for CRUD operations for Restaurants")
public class RestRestaurantsController {
    private ProductsService productsService;

    @Autowired
    public RestRestaurantsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/dto")
    @ApiOperation("Returns list of all restaurants data transfer objects")
    public List<ProductDto> getAllProductsDto() {
        return productsService.getDtoData();
    }

    @GetMapping(produces = "application/json")
    @ApiOperation("Returns list of all Restaurants")
    public List<Restaurant> getPageProducts(@RequestParam(required = false) Map<String, String> requestParams){
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        // ProductFilter productFilter = new ProductFilter(requestParams);
        // Page<Product> products = productsService.findAll(productFilter.getSpec(), pageNumber);
        return productsService.findAllRestaurants();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Returns one Restaurant by id")
    public ResponseEntity<?> getOneProduct(@PathVariable @ApiParam("Id of the product to be requested. Cannot be empty") Long id) {
        if (!productsService.existsById(id)) {
            throw new ProductNotFoundException("Product not found, id: " + id);
        }
        return new ResponseEntity<>(productsService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Removes all Restaurants")
    public void deleteAllProducts() {
        productsService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Removes one Restaurant by id")
    public void deleteOneProducts(@PathVariable Long id) {
        productsService.deleteById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new Restaurant")
    public Product saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return productsService.saveOrUpdate(product);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Modifies an existing Restaurant")
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productsService.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found, id: " + product.getId());
        }
        if (product.getPrice().doubleValue() < 0.0) {
            return new ResponseEntity<>("Product's price can not be negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.OK);
    }

}