package ru.korobko.warehouse.form;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ProductForm {

    @NotNull(message = "Введите id склада")
    private Long warehouseId;

    @Pattern(regexp = "[0-9]{8}", message = "Артикул должен состоять из 8 цифр")
    private String vendorCode;

    @Size(min = 4, max = 20, message = "Длина имени не должна быть менее 4 символов или превышать 20 символов")
    @Pattern(regexp = "[а-яА-Яa-zA-Z0-9_\\.-]*", message = "В названии допускается использовать только буквы, цифры, нижние подчёркивания, точки и дефисы")
    private String name;

    @PositiveOrZero(message = "Цена не может быть отрицательной")
    private Integer lastPurchasePrice;

    @PositiveOrZero(message = "Цена не может быть отрицательной")
    private Integer lastSalePrice;
}
