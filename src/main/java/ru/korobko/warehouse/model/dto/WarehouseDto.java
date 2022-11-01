package ru.korobko.warehouse.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class WarehouseDto implements Serializable {

    private Long id;

    private String name;

    public WarehouseDto(String name) {
        this.name = name;
    }
}
