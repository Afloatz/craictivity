package com.webapp.craictivity.entity;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    @OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL, mappedBy = "user")
    private Participant participant;

}
