package com.example.demo.dao;

import com.example.demo.model.projections.LoginDetailsView;
import org.springframework.validation.annotation.Validated;

@Validated
public interface LoginDao {

    LoginDetailsView getLogin(String username, String password);
}
