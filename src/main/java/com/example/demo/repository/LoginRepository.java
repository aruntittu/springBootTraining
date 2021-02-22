package com.example.demo.repository;

import com.example.demo.model.Login;
import com.example.demo.model.projections.LoginDetailsView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {

    LoginDetailsView findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
