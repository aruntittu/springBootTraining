package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @NoArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "login_id")
    private long id;

    @NaturalId
    @NotNull
    @Column(unique = true)
    private String username;

    @Column
    @NotNull
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
}
