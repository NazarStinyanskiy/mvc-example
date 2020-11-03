package com.company.controllers;

import com.company.data.Product;
import com.company.data.ProductsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsDAO productsDAO;

    @Autowired
    public ProductsController(ProductsDAO productsDAO){
        this.productsDAO = productsDAO;
    }

    @GetMapping
    public String allProducts(Model model){
        model.addAttribute("products", productsDAO.getProducts());
        return "products/products";
    }

    @GetMapping("/{id}")
    public String productInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productsDAO.getProduct(id));
        return "products/productInfo";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "products/newProductForm";
    }

    @PostMapping("/successCreation")
    public String successCreation(@ModelAttribute("product") Product product){
        System.out.println("ID = " + product.getId());
        productsDAO.save(product);
        return "products/successCreation";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productsDAO.getProduct(id));
        return "/products/editForm";
    }

    @PatchMapping("/{id}")
    public String editedProduct(@ModelAttribute("product") Product product, @PathVariable("id") int id, Model model){
        productsDAO.edit(id, product);
        return "products/productInfo";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productsDAO.delete(id);
        return "redirect:/products";
    }

    public ProductsDAO getProductsDAO() {
        return productsDAO;
    }
}