package com.example.demo.service;

import com.example.demo.dao.LoginDao;
import com.example.demo.model.projections.LoginDetailsView;
import com.example.demo.repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class LoginService implements LoginDao {

    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public LoginDetailsView getLogin(String username, String password) {
        return loginRepository.findByUsernameAndPassword(username, password);
    }

}
