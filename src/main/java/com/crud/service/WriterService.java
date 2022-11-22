package com.crud.service;


import com.crud.model.Writer;
import com.crud.repository.Hibernate.HiberWriterRepository;
import com.crud.repository.WriterRepository;

import java.util.List;

public class WriterService {

    WriterRepository writerRepository;

    public WriterService(){
        this.writerRepository = new HiberWriterRepository();
    }

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    public Writer getById(Integer integer) {
        return writerRepository.getById(integer);
    }

    public Writer create(Writer writer) {
        return writerRepository.create(writer);
    }

    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }

    public void deleteById(Integer id) {
writerRepository.deleteById(id);

    }
}
