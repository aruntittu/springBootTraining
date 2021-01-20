package com.example.demo.repository;

import com.example.demo.model.OrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Validated
@Repository
public interface OrderRepository extends CrudRepository<OrderItems, Long> {
}
