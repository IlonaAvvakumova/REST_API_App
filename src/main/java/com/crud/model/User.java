package com.crud.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users", schema="flyway_db")

public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    Integer id;
    String name;


    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Event.class/*error id*/)
    List<Event> events;

}
