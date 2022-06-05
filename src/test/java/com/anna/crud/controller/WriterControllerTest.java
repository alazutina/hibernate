package com.anna.crud.controller;

import com.anna.crud.model.Post;
import com.anna.crud.model.PostStatus;
import com.anna.crud.model.Tag;
import com.anna.crud.model.Writer;
import com.anna.crud.repository.hibernate.HibernateWriterRepositoryImpl;
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

class WriterControllerTest {

    @Mock
    HibernateWriterRepositoryImpl writerRepository;

    WriterController writerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        writerController = new WriterController();
    }

    @Test
    public void getById() {
        ReflectionTestUtils.setField(writerController,"writerRepository",writerRepository);
        Writer w = new Writer();
        w.setId(100l);
        w.setName("Olga");
        Set<Post> postSet = new HashSet<>();
        Post post = new Post();
        Tag t = new Tag();
        Set<Tag>tagSet = new HashSet<>();
        t.setName("news");
        t.setId(100l);
        tagSet.add(t);
        post.setId(100l);
        post.setStatus("ACTIVE");
        post.setContent("Article");
        post.setTags(tagSet);
        postSet.add(post);
        w.setPosts(postSet);
        Mockito.when(writerRepository.getById(100l)).thenReturn(w);
        assertEquals(w.toString(), writerController.getById(100l).toString());
    }

    @Test
    public void getAll(){
        ReflectionTestUtils.setField(writerController,"writerRepository",writerRepository);
        List<Writer> writerList = new ArrayList<>();
        Writer w = new Writer();
        w.setId(100l);
        w.setName("Olga");
        Set<Post> listPosts = new HashSet<>();
        Post post = new Post();
        Tag t = new Tag();
        Set<Tag> tags = new HashSet<>();
        t.setName("news");
        t.setId(100l);
        tags.add(t);
        post.setId(100l);
        post.setStatus("ACTIVE");
        post.setContent("Article");
        post.setTags(tags);
        listPosts.add(post);
        w.setPosts(listPosts);
        writerList.add(w);
        Mockito.when(writerRepository.getAll()).thenReturn(writerList);
        assertEquals(writerList.toString(), writerController.getAll().toString());
    }

    @Test
    public void deleteById() {
        ReflectionTestUtils.setField(writerController,"writerRepository",writerRepository);
        doNothing().when(writerRepository).deleteById(1l);
        writerController.deleteById(1l);
        Mockito.verify(writerRepository,Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void save() {
        ReflectionTestUtils.setField(writerController,"writerRepository",writerRepository);
        Writer w = new Writer();
        w.setId(100l);
        w.setName("Olga");
        Set<Post> posts = new HashSet<>();
        Post post = new Post();
        Tag t = new Tag();
        Set<Tag> tags = new HashSet<>();
        t.setName("news");
        t.setId(100l);
        tags.add(t);
        post.setId(100l);
        post.setStatus("ACTIVE");
        post.setContent("Article");
        post.setTags(tags);
        posts.add(post);
        w.setPosts(posts);
        Mockito.when(writerRepository.save(Mockito.any(Writer.class))).thenReturn(w);
        assertEquals(w.toString(), writerController.save("Olga",posts).toString());
    }

    @Test
    public void update() {
        ReflectionTestUtils.setField(writerController,"writerRepository",writerRepository);
        Writer w = new Writer();
        w.setId(100l);
        w.setName("Olga");
        Set<Post> listPosts = new HashSet<>();
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
        listPosts.add(post);
        w.setPosts(listPosts);
        Mockito.when(writerRepository.update(Mockito.any(Writer.class))).thenReturn(w);
        assertEquals(w.toString(), writerController.update(Mockito.anyLong(),"Olga",listPosts).toString());
    }
}