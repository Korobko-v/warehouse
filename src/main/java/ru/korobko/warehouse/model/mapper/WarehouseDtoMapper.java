package ru.korobko.warehouse.model.mapper;

import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.model.dto.WarehouseDto;

public class WarehouseDtoMapper {

    /**
     * Get warehouse DTO from warehouse model
     * @param warehouse warehouse model
     * @return warehouse DTO
     */
    public static WarehouseDto getWarehouseDtoFromWarehouse(Warehouse warehouse) {
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setName(warehouse.getName());
        warehouseDto.setId(warehouse.getId());
        return warehouseDto;
    }

    /**
     * Get warehouse model from warehouse DTO
     * @param warehouseDto warehouse DTO
     * @return warehouse model
     */
    public static Warehouse getWarehouseFromWarehouseDto(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseDto.getId());
        warehouse.setName(warehouseDto.getName());
        return warehouse;
    }
}
