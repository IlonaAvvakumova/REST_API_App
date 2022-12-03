package com.crud.repository.Interface;

import com.crud.model.FileDB;

import java.util.List;

public interface FileRepository {
    List<FileDB> getAll();

    FileDB getById(Integer id);

    FileDB create(FileDB t);

     void deleteById(Integer id);
}
