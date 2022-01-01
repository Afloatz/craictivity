package com.webapp.craictivity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//Additional table to have a ManyToMany relationship between Workshop and Participant
//and an additional attribute paid
@Entity
@Getter @Setter @NoArgsConstructor
public class Enrollment {

    @Id //for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    private boolean paid;

    public Enrollment(Participant participant, Workshop workshop, boolean paid) {
        this.participant = participant;
        this.workshop = workshop;
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
