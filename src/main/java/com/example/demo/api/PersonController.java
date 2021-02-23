package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public long addPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping
    public Iterable<Person> getAllPersons() {
        return personService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "{id}")
    public Person getPerson(@PathVariable(value = "id") long id) {
        return personService.findById(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable(value = "id") int id, @RequestBody Person person) {
        personService.updatePersonById(id, person);
    }

    @DeleteMapping(path = "{id}")
    public HttpStatus deletePerson(@PathVariable(value = "id") int id) {
         return personService.deletePersonById(id);
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public String handleTransactionSystemException(TransactionSystemException e) {
        Throwable cause = ((TransactionSystemException) e).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            return "Validation failed for Person properties. Email must not be null";
        } else {
            return e.getLocalizedMessage();
        }
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleHttpClientErrorException(HttpClientErrorException e) {
        return "Verify all the properties of request.";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable cause = ((DataIntegrityViolationException) e).getRootCause();
        if (cause instanceof SQLIntegrityConstraintViolationException) {
            return "A Person with same username already exists";
        } else {
            return e.getLocalizedMessage();
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return "Invalid Request, Person ID should be a number";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return "Person with that ID doesn't exist";
    }
}