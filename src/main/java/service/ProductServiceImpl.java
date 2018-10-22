package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> productMap;

    static {
        productMap = new HashMap<>();
        productMap.put(1, new Product(1, "1", 999, "For near rich", "Apple"));
        productMap.put(2, new Product(2, "iPhone XS", 1299, "For rich", "Apple"));
        productMap.put(3, new Product(3, "Galaxy S9", 999, "For near rich", "Samsung"));
        productMap.put(4, new Product(4, "Note 9", 1299, "For rich", "Samsung"));
        productMap.put(5, new Product(5, "HTC One", 999, "For near rich", "HTC"));
        productMap.put(6, new Product(6, "Xperia 10", 999, "For near rich", "Sony"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void save(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public Product findByID(int id) {
        return productMap.get(id);
    }

    @Override
    public Product findByName(String prodname) {
        return productMap.get(prodname);
    }

    @Override
    public void update(int id, Product product) {
        productMap.put(id, product);
    }

    @Override
    public void remove(int id) {
        productMap.remove(id);
    }
}
