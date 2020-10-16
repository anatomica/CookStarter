package ru.anatomica.cookstarter.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.dtos.ProductDto;
import ru.anatomica.cookstarter.exceptions.ProductNotFoundException;
import ru.anatomica.cookstarter.services.ProductsService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anatomica.cookstarter.utils.ProductFilter;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8189")
@RequestMapping("/api/v1/products")
@Api("Set of endpoints for CRUD operations for Products")
public class RestProductsController {
    private ProductsService productsService;

    @Autowired
    public RestProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/dto")
    @ApiOperation("Returns list of all restaurants data transfer objects")
    public List<ProductDto> getAllProductsDto() {
        return productsService.getDtoData();
    }

    @GetMapping(produces = "application/json")
    @ApiOperation("Returns list or page of all products")
    public List<Product> getPageProducts(@RequestParam(required = false) Map<String, String> requestParams){
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        ProductFilter productFilter = new ProductFilter(requestParams);
        Page<Product> products = productsService.findAll(productFilter.getSpec(), pageNumber);
        return products.getContent();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Returns list products by restaurant id")
    public ResponseEntity<?> getAllProductsByRestaurant(@PathVariable @ApiParam("Id of the restaurant to be requested. Cannot be empty") Long id) {
        if (!productsService.existsById(id)) {
            throw new ProductNotFoundException("Restaurant not found, id: " + id);
        }
        return new ResponseEntity<>(productsService.findAllProductsByRestaurant(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new Product")
    public Product saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return productsService.saveOrUpdate(product);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Modifies an existing Product")
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productsService.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found, id: " + product.getId());
        }
        if (product.getPrice().doubleValue() < 0.0) {
            return new ResponseEntity<>("Product's price can not be negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Removes one Product by id")
    public void deleteOneProducts(@PathVariable Long id) {
        productsService.deleteById(id);
    }

}