package ru.anatomica.cookstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.ProductMenu;
import ru.anatomica.cookstarter.entities.dtos.ProductDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsMenuRepository extends JpaRepository<ProductMenu, Long>, JpaSpecificationExecutor<ProductMenu> {
    List<ProductMenu> findAllByRestaurantId(Long id);
}