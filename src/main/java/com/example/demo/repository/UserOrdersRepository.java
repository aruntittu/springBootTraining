package com.example.demo.repository;

import com.example.demo.model.Person;
import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.UserOrderView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersRepository extends CrudRepository<UserOrders, Long> {

    @Query("SELECT o.id as id, o.createdDate as createdDate FROM UserOrders o WHERE o.person = :person")
    Iterable<UserOrderView> findUserOrdersByPerson(@Param("person") Person person);

    @Query(value = "{call GET_TOTAL_ORDERS_BY_PERSON(:p_id)}", nativeQuery = true)
    int GET_TOTAL_ORDERS_BY_PERSON(@Param("p_id") long person_id);
}
