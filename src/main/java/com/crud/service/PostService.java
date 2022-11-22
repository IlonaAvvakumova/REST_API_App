package com.crud.service;

import com.crud.model.Post;
import com.crud.repository.Hibernate.HiberPostRepository;
import com.crud.repository.PostRepository;

import java.util.List;

public class PostService {

    private final  PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostService(){
        this.postRepository = new HiberPostRepository();
    }
    public List<Post> getAll() {
        return postRepository.getAll();
    }


    public Post getById(Integer integer) {
        return postRepository.getById(integer);
    }


    public Post create(Post post) {
        return postRepository.create(post);
    }


    public Post update(Post post) {
        return postRepository.update(post);
    }


    public void deleteById(Integer id) {
        postRepository.deleteById(id);

    }

}
