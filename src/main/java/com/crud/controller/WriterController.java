package com.crud.controller;

import com.crud.model.Post;
import com.crud.model.Writer;
import com.crud.service.WriterService;

import java.util.List;

public class WriterController {

    private final WriterService writerService = new WriterService();

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public Writer getById(Integer integer) {
        return writerService.getById(integer);
    }

    public Writer create(String first, String last) {
        Writer writer = new Writer();
        writer.setFirstName(first);
        writer.setLastName(last);
        return writerService.create(writer);
    }

    public Writer update(Integer id, String first, String last, List<Post> postList) {
        Writer writer = new Writer();
        writer.setId(id);
        writer.setFirstName(first);
        writer.setLastName(last);
        writer.setPosts(postList);
        return writerService.update(writer);
    }

    public void deleteById(Integer id) {
        writerService.deleteById(id);
    }

}
