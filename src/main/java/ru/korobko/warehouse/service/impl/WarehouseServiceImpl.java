package ru.korobko.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.model.dto.WarehouseDto;
import ru.korobko.warehouse.model.mapper.WarehouseDtoMapper;
import ru.korobko.warehouse.repository.ProductRepository;
import ru.korobko.warehouse.repository.WarehouseRepository;
import ru.korobko.warehouse.service.WarehouseService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll().stream().sorted(Comparator.comparingLong(Warehouse::getId)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public WarehouseDto createWarehouse(WarehouseDto warehouseDto) {
        Warehouse warehouse = WarehouseDtoMapper.getWarehouseFromWarehouseDto(warehouseDto);
        Warehouse created = warehouseRepository.insert(warehouse);
        return WarehouseDtoMapper.getWarehouseDtoFromWarehouse(created);
    }

    @Transactional
    @Override
    public Warehouse show(Long id) {
        return warehouseRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void update(Long id, WarehouseDto warehouseDto) {
        Warehouse toUpdate = show(id);
        toUpdate.setName(warehouseDto.getName());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void addProduct(Long id, Product product) {
        Warehouse warehouse = show(id);
        List<Product> products = warehouse.getProducts();
        products.add(product);
        warehouse.setProducts(products);
        warehouseRepository.save(warehouse);
    }
}
