package ru.korobko.warehouse.repository;

import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.Warehouse;

@RepositoryRestResource(
        collectionResourceRel = "warehouses",
        itemResourceRel = "warehouse"
)
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @SneakyThrows
    @Transactional
    default Warehouse insert(Warehouse warehouse) {
        save(warehouse);
        return warehouse;
    }

    Warehouse findByName(String name);
}
