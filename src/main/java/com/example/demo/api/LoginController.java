package com.example.demo.api;

import com.example.demo.model.Login;
import com.example.demo.model.Person;
import com.example.demo.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/login")
@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public long validateLogin(@RequestBody Login login) {
        Person person = loginService.getLogin(login.getUsername(), login.getPassword());
        if(person!=null) {
            return person.getId();
        } else {
            return -1;
        }
    }
}
