package ru.korobko.warehouse.model.mapper;

import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.model.dto.WarehouseDto;

public class WarehouseDtoMapper {

    public static WarehouseDto getWarehouseDtoFromWarehouse(Warehouse warehouse) {
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setName(warehouse.getName());
        warehouseDto.setId(warehouse.getId());
        return warehouseDto;
    }

    public static Warehouse getWarehouseFromWarehouseDto(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseDto.getId());
        warehouse.setName(warehouseDto.getName());
        return warehouse;
    }
}
