package ru.anatomica.cookstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.anatomica.cookstarter.entities.Restaurant;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
}