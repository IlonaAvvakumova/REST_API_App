package com.crud.controller;

import com.crud.model.Label;
import com.crud.model.Post;
import com.crud.model.PostStatus;
import com.crud.model.Writer;
import com.crud.service.PostService;
import com.crud.service.WriterService;

import java.time.LocalDateTime;
import java.util.List;

public class PostController {

    private final PostService postService = new PostService();
    private final WriterService writerService = new WriterService();

    public List<Post> getAll() {
        return postService.getAll();
    }


    public Post getById(Integer integer) {
        return postService.getById(integer);
    }


    public Post create(String content, List<Label> labelList, long created, Writer writer) {
        Post post = new Post();
        post.setStatus(PostStatus.ACTIVE);
        post.setCreated(created);
        post.setWriter(writer);
        post.setContent(content);
        post.setLabelList(labelList);
        return postService.create(post);
    }

    public Post update(Integer id, String content, long created, List<Label> labelList) {
        Post post = new Post();
        long time = LocalDateTime.now().getNano() * 1000L;
        Writer writer = writerService.getById(id);
        post.setWriter(writer);
        post.setStatus(PostStatus.UNDER_REVIEW);
        post.setCreated(created);
        post.setUpdated(time);

        post.setId(id);
        post.setContent(content);
        post.setLabelList(labelList);
        return postService.update(post);
    }

    public void deleteById(Integer id) {
        postService.deleteById(id);
    }
}
