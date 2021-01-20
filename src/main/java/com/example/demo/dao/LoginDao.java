package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.validation.annotation.Validated;

@Validated
public interface LoginDao {

    Person getLogin(String username, String password);
}
