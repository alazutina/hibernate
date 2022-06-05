package com.anna.crud.controller;

import com.anna.crud.model.Post;
import com.anna.crud.model.PostStatus;
import com.anna.crud.model.Tag;
import com.anna.crud.repository.hibernate.HibernatePostRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

class PostControllerTest {
    @Mock
    HibernatePostRepositoryImpl postRepository;

    PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        postController = new PostController();
    }

    @Test
    public void getById() {
        ReflectionTestUtils.setField(postController,"postRepository",postRepository);
        Post post = new Post();
        Tag t = new Tag();
        Set<Tag> tagSet = new HashSet<>();
        t.setName("news");
        t.setId(100l);
        tagSet.add(t);
        post.setId(100l);
        post.setStatus("ACTIVE");
        post.setContent("Article");
        post.setTags(tagSet);
        Mockito.when(postRepository.getById(100l)).thenReturn(post);
        assertEquals(post.toString(), postController.getById(100l).toString());
    }

    @Test
    public void getAll(){
        ReflectionTestUtils.setField(postController,"postRepository",postRepository);
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        Tag t = new Tag();
        Set<Tag> set = new HashSet<>();
        t.setName("news");
        t.setId(100l);
        set.add(t);
        post.setId(100l);
        post.setStatus("ACTIVE");
        post.setContent("Article");
        post.setTags(set);
        posts.add(post);
        Mockito.when(postRepository.getAll()).thenReturn(posts);
        assertEquals(posts.toString(), postController.getAll().toString());
    }

    @Test
    public void deleteById() {
        ReflectionTestUtils.setField(postController,"postRepository",postRepository);
        doNothing().when(postRepository).deleteById(1l);
        postController.deleteById(1l);
        Mockito.verify(postRepository,Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void save() {
        ReflectionTestUtils.setField(postController,"postRepository",postRepository);
        Post post = new Post();
        Tag t = new Tag();
        Set<Tag> list = new HashSet<>();
        t.setName("news");
        t.setId(100l);
        list.add(t);
        post.setId(100l);
        post.setStatus("ACTIVE");
        post.setContent("Article");
        post.setTags(list);
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
        assertEquals(post.toString(), postController.save("Article",list,"ACTIVE").toString());
    }

    @Test
    public void update() {
        ReflectionTestUtils.setField(postController,"postRepository",postRepository);
        Post post = new Post();
        Tag t = new Tag();
        Set<Tag> list = new HashSet<>();
        t.setName("news");
        t.setId(100l);
        list.add(t);
        post.setId(100l);
        post.setStatus("ACTIVE");
        post.setContent("Article");
        post.setTags(list);
        Mockito.when(postRepository.update(Mockito.any(Post.class))).thenReturn(post);
        assertEquals(post.toString(), postController.update(Mockito.anyLong(),"Article",list,"ACTIVE").toString());
    }

}