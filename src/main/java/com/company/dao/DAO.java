package com.company.dao;

import com.company.data.Product;
import java.util.List;

public interface DAO {
    void save(Product product);

    List<Product> getProducts();

    Product getProduct(int id);

    void edit(int id, Product editedProduct);

    void delete(int id);
}