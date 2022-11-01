package ru.korobko.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korobko.warehouse.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
