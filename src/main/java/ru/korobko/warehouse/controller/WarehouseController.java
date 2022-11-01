package ru.korobko.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.korobko.warehouse.form.WarehouseForm;
import ru.korobko.warehouse.model.dto.WarehouseDto;
import ru.korobko.warehouse.service.WarehouseService;

import javax.validation.Valid;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

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
}