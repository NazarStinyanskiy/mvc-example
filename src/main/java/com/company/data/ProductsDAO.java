package com.company.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsDAO {
    private List<Product> products;
    private static int SERIAL_ID = 0;

    {
        this.products = new ArrayList<>();

        products.add(new Product(++SERIAL_ID, "Bread", 100, 20.20));
        products.add(new Product(++SERIAL_ID, "Milk", 30, 24.00));
        products.add(new Product(++SERIAL_ID, "Nutella", 24, 89.99));
        products.add(new Product(++SERIAL_ID, "Tomato", 500, 68.50));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id){
        return products.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    public void save(Product product){
        product.setId(++SERIAL_ID);
        products.add(product);
    }

    public void edit(int id, Product editedProduct){
        Product product = getProduct(id);
        product.setAmount(editedProduct.getAmount());
        product.setName(editedProduct.getName());
        product.setCost(editedProduct.getCost());
    }

    public void delete(int id){
        products.removeIf(x -> x.getId() == id);
    }
}
