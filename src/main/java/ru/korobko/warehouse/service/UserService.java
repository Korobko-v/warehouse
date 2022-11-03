package ru.korobko.warehouse.service;

import ru.korobko.warehouse.model.User;

import java.util.List;

public interface UserService {

    /**
     * Get all users
     * @return user list
     */
    List<User> index();

    /**
     * Save user
     * @param user user to save
     * @return saved user
     */
    User insert(User user);

    /**
     * Find user by username
     * @param username username
     * @return user
     */
    User findByUsername(String username);
}