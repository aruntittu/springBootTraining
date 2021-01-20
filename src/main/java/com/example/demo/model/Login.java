package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Login {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "login_id")
    private long id;

    @NaturalId
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @JsonBackReference
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Login(String username, String password, Person person) {
        this.username = username;
        this.password = password;
        this.person = person;
    }

    public Login() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
