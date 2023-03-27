package com.example.projectgord8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_actions")
public class UserAction {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "date_actions")
    private Date dateActions;

    @Column(name = "description")
    private String description;
}

