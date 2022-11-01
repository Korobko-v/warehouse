package ru.korobko.service;

import ru.korobko.warehouse.model.User;

import java.util.List;

public interface UserService {

    List<User> index();

    void save (User user);

    void insert(User user);

    void update(Long id, User updatedUser);

    void delete(Long id);

    User show(Long id);

    User findByUsername(String username);
}
