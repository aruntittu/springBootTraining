package com.example.webservices.restfulwebservices.versioning;

public class Name {

    private String firstName;
    private String LastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        LastName = lastName;
    }

    public Name() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
