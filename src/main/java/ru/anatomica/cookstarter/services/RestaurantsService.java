package ru.anatomica.cookstarter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.Restaurant;
import ru.anatomica.cookstarter.entities.dtos.ProductDto;
import ru.anatomica.cookstarter.repositories.ProductsRepository;
import ru.anatomica.cookstarter.repositories.RestaurantsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantsService {
    private RestaurantsRepository restaurantsRepository;

    @Autowired
    public void setRestaurantsRepository(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    public List<Restaurant> findAllRestaurants() {
        return restaurantsRepository.findAll();
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurantsRepository.findById(id);
    }

    public Restaurant saveOrUpdate(Restaurant restaurant) {
        return restaurantsRepository.save(restaurant);
    }

    public void deleteById(Long id) {
        restaurantsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return restaurantsRepository.existsById(id);
    }

}
