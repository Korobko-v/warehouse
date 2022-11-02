package ru.korobko.warehouse.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class WarehouseForm {

    @Size(min = 4, max = 20, message = "Длина имени не должна быть менее 4 символов или превышать 20 символов")
    @Pattern(regexp = "[а-яА-Яa-zA-Z0-9_\\.-]*", message = "В названии допускается использовать только буквы, цифры, нижние подчёркивания, точки и дефисы")
    private String name;
}
