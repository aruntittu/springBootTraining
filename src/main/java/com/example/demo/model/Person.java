package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "person_id")
    private long id;

    @Column
    private String name;

    @NotNull
    @Email
    @Column
    private String email;

    @Column
    private String phone;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "person", cascade=CascadeType.ALL)
    private Login login;

    public Person(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
