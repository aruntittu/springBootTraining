package com.example.demo.service;

import com.example.demo.dao.UserOrdersDao;
import com.example.demo.model.Person;
import com.example.demo.model.UserOrders;
import com.example.demo.model.projections.UserOrderView;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserOrdersRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class UserOrdersService implements UserOrdersDao {

    private UserOrdersRepository userOrdersRepository;
    private PersonRepository personRepository;

    public UserOrdersService(UserOrdersRepository userOrdersRepository, PersonRepository personRepository) {
        this.userOrdersRepository = userOrdersRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void save(UserOrders userOrder) {
        this.userOrdersRepository.save(userOrder);
    }

    @Override
    public Iterable<UserOrderView> findUserOrderByPerson(long personId) {
        if(personRepository.findById(personId).isPresent()) {
            Person person = new Person();
            person.setId(personId);
            return this.userOrdersRepository.findUserOrdersByPerson(person);
        } else {
            throw new DataIntegrityViolationException("User with that ID doesn't exist");
        }
    }

    @Override
    public int totalOrders(long person_id) {
        if(personRepository.findById(person_id).isPresent()) {
            return this.userOrdersRepository.GET_TOTAL_ORDERS_BY_PERSON(person_id);
        } else {
            throw new DataIntegrityViolationException("User with that ID doesn't exist");
        }

    }

}
