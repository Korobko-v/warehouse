package ru.korobko.warehouse.service;

import ru.korobko.warehouse.form.ProductForm;
import ru.korobko.warehouse.model.Product;

public interface ProductService {

    /**
     * Save product
     * @param product product to save
     * @return saved product
     */
    Product save(Product product);

    /**
     * Get product by ID
     * @param id product ID
     * @return product
     */
    Product show(Long id);

    /**
     * Delete product by ID
     * @param id product ID
     */
    void delete(Long id);

    /**
     * Get product by vendor code
     * @param vendorCode vendor code
     * @return product
     */
    Product findByVendorCode(String vendorCode);
}
