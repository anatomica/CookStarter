package ru.anatomica.cookstarter.repositories;

import ru.anatomica.cookstarter.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

}