package org.apache.zzz.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.zzz.Product;

import java.util.List;

public interface ProductDao {

    List<Product> selectByType(@Param("type")String type, @Param("field")String field);

    Product findProductById(long id);

    Product findProductByName(@Param("leftNum") String leftNum, @Param("name")String name);

    int insertProduct(Product product);
}
