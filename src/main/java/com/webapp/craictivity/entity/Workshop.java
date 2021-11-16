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
    private String date; //date and time of the workshop;

    //@Column(name = "duration")
    private String duration;

    //@Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //Create a third table to have a many-to-many between workshop and participant
    @JoinTable(name = "enrollment",
            joinColumns = {@JoinColumn(name = "workshop_id")},
            inverseJoinColumns = { @JoinColumn(name = "participant_id")})
    private Set<Participant> participants = new HashSet<>(); //Set is used so we cannot have duplicate elements

    public Workshop() {
    }

    public Workshop(String title, String date, String duration, double price) {
        this.title = title;
        this.date = date;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
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
