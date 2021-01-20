package com.example.demo.repository;

import com.example.demo.model.Login;
import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {

    @Query("SELECT l.person from Login as l where l.username = :username and l.password = :password")
    Person findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
