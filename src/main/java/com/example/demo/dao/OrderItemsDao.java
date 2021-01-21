package com.example.demo.dao;

import com.example.demo.model.OrderItems;
import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.OrderDetailsView;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderItemsDao {

    void save(long personId);
    Iterable<OrderDetailsView> getOrderDetails(UserOrders userOrder);
}
