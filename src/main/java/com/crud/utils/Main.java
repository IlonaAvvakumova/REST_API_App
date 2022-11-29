package com.crud.utils;


import com.crud.model.FileDB;
import com.crud.repository.HibernateNew.HiberFileRepository;

public class Main {
    public static void main(String[] args) {
        HiberFileRepository hiberFileRepository = new HiberFileRepository();
        FileDB fileDB1 = new FileDB();

        hiberFileRepository.create(fileDB1);
    }
}
