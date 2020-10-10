package ru.anatomica.cookstarter.repositories;

import ru.anatomica.cookstarter.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {
}
