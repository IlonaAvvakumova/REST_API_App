package com.crud.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "files", schema="flyway_db")

public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String filePath;
}
