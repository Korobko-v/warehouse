package ru.korobko.warehouse.repository;

import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.User;
import ru.korobko.warehouse.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @SneakyThrows
    @Transactional
    default Warehouse insert(Warehouse warehouse) {
        save(warehouse);
        return warehouse;
    }
}
