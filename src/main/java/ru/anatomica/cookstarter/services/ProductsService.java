package ru.anatomica.cookstarter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.dtos.ProductDto;
import ru.anatomica.cookstarter.repositories.ProductsRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAllProducts(Long id) {
        return productsRepository.findAll();
    }

    public List<Product> findAllProductsByRestaurant(Long id) {
        return productsRepository.findAllByRestaurantId(id);
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
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
