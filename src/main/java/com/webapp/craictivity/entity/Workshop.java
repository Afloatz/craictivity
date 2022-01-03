package com.webapp.craictivity.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Data
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//@Table(name = "workshops")
public class Workshop {

    @Id //for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "title", nullable = false)
    private String title;
    private String date; //date of the workshop;
    private String time;
    private double duration;
    private int price;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "workshop")
    private Set<Enrollment> enrollments = new HashSet<>(); //Set is used so we cannot have duplicate elements

    public Workshop(String title, String date, String time, double duration, int price, Instructor instructor) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.instructor = instructor;
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
