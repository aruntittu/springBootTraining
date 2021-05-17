package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}