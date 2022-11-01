package ru.korobko.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korobko.warehouse.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
