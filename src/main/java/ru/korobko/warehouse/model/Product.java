package ru.korobko.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "name")
    private String name;

    @Column(name = "last_purchase_price")
    private Integer lastPurchasePrice;

    @Column(name = "last_sale_price")
    private Integer lastSalePrice;
}
