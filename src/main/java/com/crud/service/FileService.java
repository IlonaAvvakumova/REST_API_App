package com.crud.service;

import com.crud.model.FileEntity;
import com.crud.repository.HiberFileRepository;

import java.util.List;

public class FileService {

    HiberFileRepository hiberFileRepository = new HiberFileRepository();

    public FileService() {
    }

    public FileService(HiberFileRepository hiberFileRepository) {
        this.hiberFileRepository = hiberFileRepository;
    }


    public List<FileEntity> getAll() {
        return hiberFileRepository.getAll();
    }


    public FileEntity getById(Integer id) {
        return hiberFileRepository.getById(id);
    }


    public FileEntity create(FileEntity fileEntity) {

        return hiberFileRepository.create(fileEntity);

    }


    public void deleteById(Integer id) {
        hiberFileRepository.deleteById(id);
    }
}
