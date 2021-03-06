package com.webapp.craictivity.entity;

import javax.persistence.*;

import lombok.*;


@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 45, nullable = false)
    private String firstName;
    @Column(length = 45, nullable = false)
    private String lastName;
    @Column(length = 45, nullable = false)
    private String email;

//    public Instructor(String firstName, String lastName, String email) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }

}
