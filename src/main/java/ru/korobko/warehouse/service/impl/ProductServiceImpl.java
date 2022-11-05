package ru.korobko.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korobko.warehouse.model.Product;
import ru.korobko.warehouse.repository.ProductRepository;
import ru.korobko.warehouse.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product show(Long id) {
        return productRepository.findById(id).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product findByVendorCode(String vendorCode) {
        return productRepository.findByVendorCode(vendorCode);
    }
}
