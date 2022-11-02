package ru.korobko.warehouse.service;

import ru.korobko.warehouse.form.ProductForm;
import ru.korobko.warehouse.model.Product;

public interface ProductService {

    Product save(Product product);
}
