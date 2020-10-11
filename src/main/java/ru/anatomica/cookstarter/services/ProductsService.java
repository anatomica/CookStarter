package ru.anatomica.cookstarter.services;

import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.ProductMenu;
import ru.anatomica.cookstarter.entities.Restaurant;
import ru.anatomica.cookstarter.entities.dtos.ProductDto;
import ru.anatomica.cookstarter.repositories.ProductsMenuRepository;
import ru.anatomica.cookstarter.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.anatomica.cookstarter.repositories.RestaurantsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;
    private RestaurantsRepository restaurantsRepository;
    private ProductsMenuRepository productsMenuRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Autowired
    public void setRestaurantsRepository(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @Autowired
    public ProductsService(ProductsMenuRepository productsMenuRepository) {
        this.productsMenuRepository = productsMenuRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return productsRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }

    public List<Restaurant> findAllRestaurants() {
        return restaurantsRepository.findAll();
    }

    public List<ProductMenu> findAllProductsByRestaurant(Long id) {
        return productsMenuRepository.findAllByRestaurantId(id);
    }

    public void deleteAll() {
        productsRepository.deleteAll();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return productsRepository.existsById(id);
    }

    public List<ProductDto> getDtoData() {
        return productsRepository.findAllBy();
    }
}
