package ru.korobko.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.korobko.warehouse.model.Product;

@RepositoryRestResource(
        collectionResourceRel = "products",
        itemResourceRel = "product"
)
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByVendorCode(String vendorCode);
}
