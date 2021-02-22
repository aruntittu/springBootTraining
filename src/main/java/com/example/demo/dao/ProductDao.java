package com.example.demo.dao;

import com.example.demo.model.Product;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductDao{

    void save(Product product) ;
    void updateById(int id, Product product) ;
    void deleteProductById(int id);
    Product get(Integer id);
    Iterable<Product> getAll();

}