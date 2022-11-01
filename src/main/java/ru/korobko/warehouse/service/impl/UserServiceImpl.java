package ru.korobko.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.User;
import ru.korobko.warehouse.repository.UserRepository;
import ru.korobko.warehouse.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<User> index() {
        return userRepository.findAll();
    }

    @Transactional
    public void save (User user) {
        userRepository.save(user);
    }

    @Transactional
    public void insert(User user) {
        userRepository.insert(user);
    }

    @Transactional
    public void update(Long id, User updatedUser) {
        User toUpdate = show(id);
        toUpdate.setUsername(updatedUser.getUsername());
        toUpdate.setRole(updatedUser.getRole());
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User show(Long id) {
        return userRepository.getById(id);
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
