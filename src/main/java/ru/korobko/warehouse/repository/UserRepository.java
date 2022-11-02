package ru.korobko.warehouse.repository;

import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @SneakyThrows
    @Transactional
    default User insert(User user) {
        return save(user);
    }
}
