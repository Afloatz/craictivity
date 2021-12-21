package com.webapp.craictivity.entity;

import javax.persistence.*;

//Additional table to have a ManyToMany relationship between Workshop and Participant
//and an additional attribute paid
@Entity
public class Enrollment {

    @Id //for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    Participant participant;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    Workshop workshop;

    boolean paid;

    public Enrollment() {
    }

    public Enrollment(Participant participant, Workshop workshop, boolean paid) {
        this.participant = participant;
        this.workshop = workshop;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "participant=" + participant +
                ", workshop=" + workshop +
                ", paid=" + paid +
                '}';
    }
}
