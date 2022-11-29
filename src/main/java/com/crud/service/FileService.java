package com.crud.service;

import com.crud.model.FileDB;
import com.crud.repository.HibernateNew.HiberFileRepository;

import java.util.List;

public class FileService {

    HiberFileRepository hiberFileRepository = new HiberFileRepository();

    public FileService() {
    }

    public FileService(HiberFileRepository hiberFileRepository) {
        this.hiberFileRepository = hiberFileRepository;
    }


    public List<FileDB> getAll() {
        return hiberFileRepository.getAll();
    }


    public FileDB getById(Integer id) {
        return hiberFileRepository.getById(id);
    }


    public FileDB create(FileDB fileDB) {
        return hiberFileRepository.create(fileDB);

    }


    public void deleteById(Integer id) {
        hiberFileRepository.deleteById(id);
    }
}
