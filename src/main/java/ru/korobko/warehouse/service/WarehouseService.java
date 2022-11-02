package ru.korobko.warehouse.service;

import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.model.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService {

    @Transactional
    List<Warehouse> getAllWarehouses();

    WarehouseDto createWarehouse(WarehouseDto warehouseDto);

    @Transactional
    Warehouse show(Long id);

    @Transactional
    void update(Long id, WarehouseDto warehouseDto);

    @Transactional
    void delete(Long id);

    @Transactional
    void addProduct(Long id, Product product);

    @Transactional
    Warehouse save(Warehouse warehouse);
}
