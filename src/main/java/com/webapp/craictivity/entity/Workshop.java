package com.webapp.craictivity.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "workshops")
public class Workshop {

    @Id //for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "title", nullable = false)
    private String title;

    //@Column(name = "date")
    private String date; //date of the workshop;

    private String time;

    //@Column(name = "duration")
    private double duration;

    //@Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    //Create a third table to have a many-to-many between workshop and participant
//    @JoinTable(name = "enrollment",
//            joinColumns = {@JoinColumn(name = "workshop_id")},
//            inverseJoinColumns = { @JoinColumn(name = "participant_id")})
//    private Set<Participant> participants = new HashSet<>(); //Set is used so we cannot have duplicate elements
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "workshop")
    private Set<Enrollment> enrollments = new HashSet<>(); //Set is used so we cannot have duplicate elements

    public Workshop() {
    }

    public Workshop(String title, String date, String time, double duration, int price) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

//    public Set<Participant> getParticipants() {
//        return participants;
//    }
//
//    public void setParticipants(Set<Participant> participants) {
//        this.participants = participants;
//    }


    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
