package com.webapp.craictivity.entity;

import javax.persistence.*;

//Additional table to have a ManyToMany relationship between Workshop and Participant
@Entity
public class Enrollment {

    @EmbeddedId
    EnrollmentKey id = new EnrollmentKey();

    @ManyToOne
    @MapsId("participantId")
    @JoinColumn(name = "participant_id")
    Participant participant;

    @ManyToOne
    @MapsId("workshopId")
    @JoinColumn(name = "workshop_id")
    Workshop workshop;

    @Column(name = "has_paid", columnDefinition = "boolean default false")
    boolean hasPaid;

    public Enrollment() {
    }

    public Enrollment(Participant participant, Workshop workshop) {
        this.participant = participant;
        this.workshop = workshop;
    }

    public EnrollmentKey getId() {
        return id;
    }

    public void setId(EnrollmentKey id) {
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

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
}
