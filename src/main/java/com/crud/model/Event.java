package com.crud.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "events", schema="flyway_db")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "file_id")
    FileDB fileDB;


}
