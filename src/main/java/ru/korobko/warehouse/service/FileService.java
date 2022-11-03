package ru.korobko.warehouse.service;

import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;

import java.io.IOException;

public interface FileService {

    /**
     * Write purchase report to CSV
     * @param product product
     * @throws IOException
     */
    void purchaseCsv(Product product) throws IOException;

    /**
     * Write sale report to CSV
     * @param product product
     * @throws IOException
     */
    void saleCsv(Product product) throws IOException;

    /**
     * Write move report to CSV
     * @param product product to move
     * @param oldWarehouse old warehouse
     * @param newWarehouse new warehouse
     * @throws IOException
     */
    void moveCsv(Product product, Warehouse oldWarehouse, Warehouse newWarehouse) throws IOException;

    /**
     * Get CSV file with all products
     * @throws IOException
     */
    void allProductsCsv() throws IOException;
}
