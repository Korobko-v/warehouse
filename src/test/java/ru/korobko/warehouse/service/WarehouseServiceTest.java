package ru.korobko.warehouse.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.model.dto.WarehouseDto;
import ru.korobko.warehouse.model.mapper.WarehouseDtoMapper;

@SpringBootTest
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ProductService productService;

    @Test
    public void createUpdateAndDeleteWarehouseTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("warehouse");
        WarehouseDto warehouseDto = warehouseService.createWarehouse(WarehouseDtoMapper.getWarehouseDtoFromWarehouse(warehouse));
        Assertions.assertNotEquals(0, warehouseService.getAllWarehouses().size());
        int sizeWithCreatedWarehouse = warehouseService.getAllWarehouses().size();
        Assertions.assertEquals("warehouse", warehouseService.getAllWarehouses().get(sizeWithCreatedWarehouse - 1).getName());

        Long id = warehouseDto.getId();
        warehouseService.update(id, new WarehouseDto("updatedWarehouse"));

        Assertions.assertEquals("updatedWarehouse", warehouseService.show(id).getName());
        Assertions.assertEquals(0, warehouseService.show(id).getProducts().size());

        Product product = new Product();
        product.setName("product");
        product.setVendorCode("00000001");
        product.setLastPurchasePrice(100);
        product.setLastSalePrice(150);
        product = productService.save(product);
        warehouseService.addProduct(id, product);

        Assertions.assertNotEquals(0, warehouseService.show(id).getProducts().size());

        warehouseService.deleteProduct(id, product.getId());

        Assertions.assertEquals(0, warehouseService.show(id).getProducts().size());

        warehouseService.delete(id);
        Assertions.assertEquals(sizeWithCreatedWarehouse - 1, warehouseService.getAllWarehouses().size());
    }
}
