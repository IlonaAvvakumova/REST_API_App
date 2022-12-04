package com.crud.repository.Interface;

import com.crud.model.FileEntity;

import java.util.List;

public interface FileRepository {
    List<FileEntity> getAll();

    FileEntity getById(Integer id);

    FileEntity create(FileEntity t);

     void deleteById(Integer id);
}
