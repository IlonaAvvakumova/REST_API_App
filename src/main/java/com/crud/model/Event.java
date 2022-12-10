package com.crud.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.*;
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

    @JsonBackReference
    User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "file_id")
    FileEntity fileEntity;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", user=" + user.getName() +
                ", fileEntity=" + fileEntity.getName() +
                '}';
    }
}
