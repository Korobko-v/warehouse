package ru.korobko.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korobko.warehouse.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
