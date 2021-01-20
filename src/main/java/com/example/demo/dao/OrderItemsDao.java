package com.example.demo.dao;

import com.example.demo.model.OrderItems;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderItemsDao {

    void save(long personId);
}
