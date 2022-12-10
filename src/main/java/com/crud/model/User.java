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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Event.class)
    List<Event> eventEntities;

}
