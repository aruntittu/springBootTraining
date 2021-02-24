package com.example.demo.service;

import com.example.demo.dao.UserOrdersDao;
import com.example.demo.model.Person;
import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.UserOrderView;
import com.example.demo.repository.UserOrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Validated
@Service
public class UserOrdersService implements UserOrdersDao {

    private UserOrdersRepository userOrdersRepository;
    private EntityManager entityManager;

    public UserOrdersService(UserOrdersRepository userOrdersRepository, EntityManager entityManager) {
        this.userOrdersRepository = userOrdersRepository;
        this.entityManager = entityManager;
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
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_TOTAL_ORDERS_BY_PERSON")
                .registerStoredProcedureParameter("person_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("total_orders", Integer.class, ParameterMode.OUT);

        query.setParameter("person_id", person_id);
        query.execute();
        return (int) query.getOutputParameterValue("total_orders");
    }

}
