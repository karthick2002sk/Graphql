package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.Product;
import com.example.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired public ProductRepository productRepository;
    public List<Product> getProduct(){
        return productRepository.findAll();
    }
    public List<Product> getProductByCategory(String category){
        return productRepository.findByCategory(category);
    }
public Product getProductsById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
    }

   public Product updateProduct(int id, String name, String category, Float price, Integer stock) {
    Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));

    if (name != null) {
        existingProduct.setName(name);
    }
    if (category != null) {
        existingProduct.setCategory(category);
    }
    if (price != null) {
        existingProduct.setPrice(price);
    }
    if (stock != null) {
        existingProduct.setStock(stock);
    }

    return productRepository.save(existingProduct);
}

     public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete product by id
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
        productRepository.delete(product);
    }
}
