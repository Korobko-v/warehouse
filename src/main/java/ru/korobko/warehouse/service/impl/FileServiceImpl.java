package ru.korobko.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.service.FileService;
import ru.korobko.warehouse.service.ProductService;
import ru.korobko.warehouse.service.WarehouseService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ProductService productService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void purchaseCsv(Product product) throws IOException {
        File file = new File("purchase_" + product.getVendorCode() + ".csv");
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
        File file = new File("sale_" + product.getVendorCode() + ".csv");
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
        File file = new File("move_" + product.getVendorCode() + ".csv");
        FileWriter writer = new FileWriter(file);
        writer.write("Товар,Склад1,Склад2,Дата\n");
        writer.write(product.getName() + "," + oldWarehouse.getName() + "," +
                newWarehouse.getName() + "," + new Date().toString());
        writer.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void allProductsCsv() throws IOException {
        File file = new File("products.csv");
        FileWriter writer = new FileWriter(file);
        for (Warehouse warehouse : warehouseService.getAllWarehouses()) {
            writer.write(warehouse.getName() + "\n");
            for (Product product : warehouse.getProducts()) {
                writer.write("Наименование,Артикул,ЦенаПоследнейЗакупки,ЦенаПоследнейПродажи\n");
                writer.write(product.getName() + "," + product.getVendorCode() + "," +
                        product.getLastPurchasePrice() + "," + product.getLastSalePrice() + "\n");
            }
        }
        writer.close();
    }
}
