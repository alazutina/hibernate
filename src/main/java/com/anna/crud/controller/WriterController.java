package com.anna.crud.controller;

import com.anna.crud.model.Post;
import com.anna.crud.model.Writer;
import com.anna.crud.repository.WriterRepository;
import com.anna.crud.repository.hibernate.HibernateWriterRepositoryImpl;
import java.util.List;
import java.util.Set;

public class WriterController  {



    private final WriterRepository writerRepository;

    public WriterController(){
        this.writerRepository = new HibernateWriterRepositoryImpl();
    }
    public WriterController(WriterRepository writerRepository){
        this.writerRepository=writerRepository;
    }

    public Writer save(String name, Set<Post> posts) {
        Writer writer = new Writer();
        writer.setPosts(posts);
        writer.setName(name);
        return writerRepository.save(writer);
    }

    public Writer update(Long id, String name, Set<Post> posts) {
        Writer writer = new Writer();
        writer.setPosts(posts);
        writer.setName(name);
        writer.setId(id);
        return writerRepository.update(writer);
    }

    public Writer getById(Long id)  {
        return writerRepository.getById(id);
    }

    public void deleteById(Long id) {
        writerRepository.deleteById(id);
    }

    public List<Writer> getAll(){
        return writerRepository.getAll();
    }
}