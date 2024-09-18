package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.Product;
import com.example.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @QueryMapping
    public List<Product> getProducts(){
       return productService.getProduct();
    }

    @QueryMapping
    public List<Product> getProductsByCategory(@Argument String category){
        return productService.getProductByCategory(category);
    }
    @QueryMapping
    public Product getProductsById(@Argument int id){
        return productService.getProductsById(id);
    }
    @MutationMapping
    public Product addProduct(@Argument String name, @Argument String category, @Argument float price, @Argument int stock) {
        Product newProduct = new Product(name, category, price, stock);
        return productService.addProduct(newProduct);
    }
    @MutationMapping
    public Product updateProduct(@Argument int id, @Argument String name, @Argument String category,
                                 @Argument Float price, @Argument Integer stock) {
        return productService.updateProduct(id, name, category, price, stock);
    }

    // Delete product by id
    @MutationMapping
    public String deleteProduct(@Argument int id) {
        productService.deleteProduct(id);
        return "Product with id " + id + " has been deleted.";
    }
}
