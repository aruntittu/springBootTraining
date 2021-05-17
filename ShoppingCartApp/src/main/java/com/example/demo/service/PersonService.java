package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Login;
import com.example.demo.model.Person;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class PersonService implements PersonDao{

    private PersonRepository personRepository;
    private LoginRepository loginRepository;

    public PersonService(PersonRepository personRepository, LoginRepository loginRepository) {
        this.personRepository = personRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public @NotNull Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public Long savePerson(Person person) {
        Person p = new Person();
        p.setName(person.getName());
        p.setEmail(person.getEmail());
        p.setPhone(person.getPhone());
        Person savedPerson = personRepository.save(p);
        if(personRepository.findById(savedPerson.getId()).isPresent()) {
            Login l = person.getLogin();
            l.setPerson(savedPerson);
            loginRepository.save(l);
            return savedPerson.getId();
        } else {
            return null;
        }
    }

    @Override
    public void updatePersonById(long id, Person person) {
        if(personRepository.findById(id).isPresent()) {
            Person exitingPerson = personRepository.findById(id).get();
            exitingPerson.setId(id);
            exitingPerson.setName(person.getName());
            personRepository.save(exitingPerson);
        }
    }

    @Override
    public HttpStatus deletePersonById(long id) {
        if(personRepository.findById(id).isPresent()) {
             personRepository.deleteById(id);
             return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @Override
    public Person findById(long id) {
        if(personRepository.findById(id).isPresent()) {
            return personRepository.findById(id).get();
        } else {
            return null;
        }
    }
}
