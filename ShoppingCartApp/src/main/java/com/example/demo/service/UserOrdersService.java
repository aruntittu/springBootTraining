package com.example.demo.service;

import com.example.demo.dao.UserOrdersDao;
import com.example.demo.model.Person;
import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.UserOrderView;
import com.example.demo.repository.UserOrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class UserOrdersService implements UserOrdersDao {

    private UserOrdersRepository userOrdersRepository;

    public UserOrdersService(UserOrdersRepository userOrdersRepository) {
        this.userOrdersRepository = userOrdersRepository;
    }

    @Override
    public void save(UserOrders userOrder) {
        this.userOrdersRepository.save(userOrder);
    }

    @Override
    public Iterable<UserOrderView> findUserOrderByPerson(long personId) {
        Person person = new Person();
        person.setId(personId);
        return this.userOrdersRepository.findUserOrdersByPerson(person);
    }

    @Override
    public int totalOrders(long person_id) {
        return this.userOrdersRepository.GET_TOTAL_ORDERS_BY_PERSON(person_id);
    }

}
