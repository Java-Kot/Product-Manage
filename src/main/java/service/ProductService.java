package service;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findByID(int id);

    Product findByName(String prodname);

    void update(int d, Product product);

    void remove(int id);
}
