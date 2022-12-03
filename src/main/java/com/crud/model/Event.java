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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "file_id")
    FileDB fileDB;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", user=" + user.getName() +
                ", fileDB=" + fileDB.getName() +
                '}';
    }
}
