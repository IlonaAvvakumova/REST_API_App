package com.crud.service;

import com.crud.model.Post;
import com.crud.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class PostServiceTest {
    PostRepository postRepository = Mockito.mock(PostRepository.class);
    PostService postService = new PostService(postRepository);

    @Test
    void getAll() {
        List<Post> list = new ArrayList<>();
        when(postRepository.getAll()).thenReturn(list);
        List<Post> postList =  postRepository.getAll();
        assertNotNull(postService.getAll());
        assertArrayEquals(list.toArray(), postList.toArray());
    }

    @Test
    void getById() {
        Post post = new Post();
        when(postRepository.getById(1)).thenReturn(post);
        assertEquals(post, postService.getById(1));
    }

    @Test
    void create() {
        Post post = new Post();
        when(postRepository.create(post)).thenReturn(post);
        assertEquals(post, postService.create(post));
    }

    @Test
    void update() {
        Post post = new Post();
        when(postRepository.update(post)).thenReturn(post);
        assertEquals(post, postService.update(post));
    }

    @Test
    void deleteById() {
        PostService postService2 = Mockito.mock(PostService.class);
        doNothing().when(postService2).deleteById(1);
    }
}