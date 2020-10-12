package ru.anatomica.cookstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.anatomica.cookstarter.entities.Product;
import ru.anatomica.cookstarter.entities.dtos.ProductDto;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByRestaurantId(Long id);
    List<ProductDto> findAllBy();
}