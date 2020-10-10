package ru.anatomica.cookstarter.repositories;

import ru.anatomica.cookstarter.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByPhone(String phone);
    Optional<User> findOneByEmail(String email);
    List<User> findAllByEmailContainsAndUserBlockFalse(String email);
    boolean existsByPhone(String phone);
}
