package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface PersonDao {

    @NotNull Iterable<Person> findAll();

    Long savePerson(Person person);

    void updatePersonById(long id, Person person);

    HttpStatus deletePersonById(long id);

    Person findById(long id);
}
