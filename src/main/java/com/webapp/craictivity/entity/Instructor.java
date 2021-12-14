package com.webapp.craictivity.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 45, nullable = false)
    private String firstName;
    @Column(length = 45, nullable = false)
    private String lastName;
    @Column(length = 45, nullable = false, unique = true)
    private String email;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Set<Workshop> getWorkshops() {
//        return workshops;
//    }
//
//    public void setWorkshops(Set<Workshop> workshops) {
//        this.workshops = workshops;
//    }
}
