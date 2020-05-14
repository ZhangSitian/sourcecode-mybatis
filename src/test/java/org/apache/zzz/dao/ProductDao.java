package org.apache.zzz.dao;

import org.apache.zzz.Product;

public interface ProductDao {
    Product findProductById(long id);

    Product findProductByName(String name);

    int insertProduct(Product product);
}
