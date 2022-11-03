package ru.korobko.warehouse.service;

import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.model.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService {

    /**
     * Get all warehouses
     * @return warehouses list
     */
    @Transactional
    List<Warehouse> getAllWarehouses();

    /**
     * Create warehouse
     * @param warehouseDto warehouse DTO
     * @return created warehouse DTO
     */
    WarehouseDto createWarehouse(WarehouseDto warehouseDto);

    /**
     * Get warehouse by ID
     * @param id ID
     * @return warehouse
     */
    @Transactional
    Warehouse show(Long id);

    /**
     * Update warehouse
     * @param id id
     * @param warehouseDto warehouse DTO
     */
    @Transactional
    void update(Long id, WarehouseDto warehouseDto);

    /**
     * Delete warehouse by ID
     * @param id ID
     */
    @Transactional
    void delete(Long id);

    /**
     * Add product to warehouse
     * @param id ID
     * @param product product to add
     */
    @Transactional
    void addProduct(Long id, Product product);

    /**
     * Save warehouse
     * @param warehouse warehouse to save
     * @return saved warehouse
     */
    @Transactional
    Warehouse save(Warehouse warehouse);

    /**
     * Remove product from warehouse
     * @param id warehouse ID
     * @param productId product ID
     */
    void deleteProduct(Long id, Long productId);
}
