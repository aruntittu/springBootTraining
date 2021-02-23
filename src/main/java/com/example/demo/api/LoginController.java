package com.example.demo.api;

import com.example.demo.model.Login;
import com.example.demo.model.projections.LoginDetailsView;
import com.example.demo.service.LoginService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        if(login.getUsername()!=null && login.getPassword()!=null) {
            LoginDetailsView person = loginService.getLogin(login.getUsername(), login.getPassword());
            if (person != null) {
                return person.getPerson().getId();
            } else {
                return -1;
            }
        } else {
            throw new DataIntegrityViolationException("Empty Username or Password");
        }
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return e.getLocalizedMessage();
    }
}
