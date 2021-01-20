package com.example.demo.dao;

import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.UserOrderView;

public interface UserOrdersDao {

    void save(UserOrders userOrder);

    Iterable<UserOrderView> findUserOrderByPerson(long personId);
}
