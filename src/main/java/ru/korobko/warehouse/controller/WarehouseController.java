package ru.korobko.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.korobko.warehouse.form.ProductForm;
import ru.korobko.warehouse.form.WarehouseForm;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.model.Warehouse;
import ru.korobko.warehouse.model.dto.WarehouseDto;
import ru.korobko.warehouse.service.FileService;
import ru.korobko.warehouse.service.ProductService;
import ru.korobko.warehouse.service.WarehouseService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @GetMapping
    public String getAllWarehouses(Model model) {
        model.addAttribute("warehousesList", warehouseService.getAllWarehouses());
        return "warehouses/index";
    }

    @PostMapping
    public WarehouseDto createWarehouse(@RequestBody WarehouseDto warehouseDto) {
        return warehouseService.createWarehouse(warehouseDto);
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("warehouseList", warehouseService.getAllWarehouses());
        model.addAttribute("warehouse", warehouseService.show(id));
        model.addAttribute("products", warehouseService.show(id).getProducts());
        return "warehouses/show";
    }

    @GetMapping("/new_warehouse")
    private String showWarehouseForm(
            @ModelAttribute("warehouseForm")
                    WarehouseForm warehouseForm) {
        return "warehouses/new_warehouse";
    }

    @PostMapping("/new_warehouse")
    public String handleWarehouseForm(@ModelAttribute("warehouseForm")
                                      @Valid
                                              WarehouseForm warehouseForm, BindingResult result) {


        if (result.hasErrors()) {
            return "warehouses/new_warehouse";
        }

        try {
            warehouseService.createWarehouse(new WarehouseDto(warehouseForm.getName()));
        } catch (Exception cause) {
            result.addError(new FieldError(
                    "form",
                    "name",
                    "Такой склад уже существует"
            ));
        }

        if (result.hasErrors()) {
            return "warehouses/new_warehouse";
        }

        return "redirect:/warehouses";
    }

    @GetMapping("/add_product")
    private String showProductForm(
            @ModelAttribute("productForm")
                    ProductForm productForm) {
        return "warehouses/add_product";
    }

    @PostMapping("add_product")
    public String handleProductForm(@ModelAttribute("productForm")
                                    @Valid
                                            ProductForm productForm,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "warehouses/add_product";
        }
        Product product = new Product();
        product.setVendorCode(productForm.getVendorCode());
        product.setName(productForm.getName());
        product.setLastPurchasePrice(productForm.getLastPurchasePrice());
        product.setLastSalePrice(productForm.getLastSalePrice());
        product = productService.save(product);
        warehouseService.addProduct(productForm.getWarehouseId(), product);

        if (result.hasErrors()) {
            return "warehouses/add_product";
        }
        try {
            fileService.purchaseCsv(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/warehouses/" + productForm.getWarehouseId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("warehouse", warehouseService.show(id));
        return "warehouses/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("warehouse") @Valid WarehouseDto warehouseDto,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {

        if (bindingResult.hasErrors())
            return "warehouses/edit";

        warehouseService.update(id, warehouseDto);
        return "redirect:/warehouses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        warehouseService.delete(id);
        return "redirect:/warehouses";
    }

    @DeleteMapping("/{id}/{productId}")
    public String deleteProduct(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
        Product product = productService.show(productId);
        warehouseService.deleteProduct(id, productId);
        try {
            fileService.saleCsv(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/warehouses";
    }

    @PatchMapping("/move-product/{oldId}/{productId}/{newId}")
    public String moveProduct(@PathVariable("oldId") Long oldWarehouseId,
                              @PathVariable("productId") Long productId,
                              @PathVariable("newId") Long newWarehouseId) {
        Product product = productService.show(productId);
        warehouseService.deleteProduct(oldWarehouseId, productId);
        warehouseService.addProduct(newWarehouseId, product);
        Warehouse oldWarehouse = warehouseService.show(oldWarehouseId);
        Warehouse newWarehouse = warehouseService.show(newWarehouseId);
        try {
            fileService.moveCsv(product, oldWarehouse, newWarehouse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/warehouses";
    }

    @GetMapping("/csv")
    public String createProductsCsvReport() {
        try {
            fileService.allProductsCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/warehouses";
    }
}
