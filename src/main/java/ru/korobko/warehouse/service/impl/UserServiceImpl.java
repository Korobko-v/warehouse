package ru.korobko.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.User;
import ru.korobko.warehouse.repository.ProductRepository;
import ru.korobko.warehouse.repository.UserRepository;
import ru.korobko.warehouse.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<User> index() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User insert(User user) {
       return userRepository.insert(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
