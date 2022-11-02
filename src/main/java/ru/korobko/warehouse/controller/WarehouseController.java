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
import ru.korobko.warehouse.service.ProductService;
import ru.korobko.warehouse.service.WarehouseService;

import javax.validation.Valid;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ProductService productService;

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
                                    BindingResult result, Model model) {
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
        Warehouse warehouse = warehouseService.show(id);
        warehouse.getProducts().removeIf(product -> product.getId().equals(productId));
        warehouseService.save(warehouse);
        return "redirect:/warehouses";
    }
}
