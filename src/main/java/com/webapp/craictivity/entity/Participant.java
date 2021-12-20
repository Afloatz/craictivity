package com.webapp.craictivity.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "participants")
//    private Set<Workshop> workshops = new HashSet<>(); //Set is used so we cannot have duplicate elements
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "participant")
    private Set<Enrollment> enrollments = new HashSet<>(); //Set is used so we cannot have duplicate elements

    public Participant() {
    }

    public Participant(String firstName, String lastName, String email, String phone, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Workshop> getWorkshops() {
//        return workshops;
//    }
//
//    public void setWorkshops(Set<Workshop> workshops) {
//        this.workshops = workshops;
//    }


    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
