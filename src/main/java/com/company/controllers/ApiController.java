package com.company.controllers;

import com.company.data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/api")
public class ApiController {

    private final ProductsController productsController;

    @Autowired
    public ApiController(ProductsController productsController){
        this.productsController = productsController;
    }

    @GetMapping("/get")
    public List<Product> getJsonAllProducts(){
        return productsController.getProductsDAO().getProducts();
    }

    @GetMapping("/get/{id}")
    public Product getJsonProduct(@PathVariable("id") int id){
        return productsController.getProductsDAO().getProduct(id);
    }
}