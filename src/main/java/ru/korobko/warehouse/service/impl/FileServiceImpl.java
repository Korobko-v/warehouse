package ru.korobko.warehouse.service.impl;

import org.springframework.stereotype.Service;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.service.FileService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void purchaseCsv(Product product) throws IOException {
        File file = new File("purchase_" + product.getName() + ".csv");
        FileWriter writer = new FileWriter(file);
        writer.write("Наименование,Артикул,Цена,Дата\n");
        writer.write(product.getName() + "," + product.getVendorCode() + "," +
                product.getLastPurchasePrice() + "," + new Date().toString());
        writer.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saleCsv(Product product) throws IOException {
        File file = new File("sale_" + product.getName() + ".csv");
        FileWriter writer = new FileWriter(file);
        writer.write("Наименование,Артикул,Цена,Дата\n");
        writer.write(product.getName() + "," + product.getVendorCode() + "," +
                product.getLastSalePrice() + "," + new Date().toString());
        writer.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCsv(Product product, Warehouse oldWarehouse, Warehouse newWarehouse) throws IOException {
        File file = new File("move_" + product.getName() + ".csv");
        FileWriter writer = new FileWriter(file);
        writer.write("Товар,Склад1,Склад2,Дата\n");
        writer.write(product.getName() + "," + oldWarehouse.getName() + "," +
                newWarehouse.getName() + "," + new Date().toString());
        writer.close();
    }
}
