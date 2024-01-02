package com.safestreets.services;

import com.safestreets.model.Product;
import com.safestreets.model.repository.ProductRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createNewProduct(String name, String description) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        return productRepository.save(product);
    }

    public Product getProduct(long id) {
        var product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product updateProduct(Product product, long id) {
        product.setId(id);
        return productRepository.update(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public boolean productExists(long id) {
        return productRepository.existsById(id);
    }

}
