package com.anna.crud.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
    @Table(name = "writers")
public class Writer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="ID")
    Long id;
        @Column(name="NAME")
        private  String name;
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable (name="writer_post",
                joinColumns=@JoinColumn (name="ID_WRITER"),
                inverseJoinColumns=@JoinColumn(name="ID_POST"))
        private Set<Post> posts = new HashSet<>();

    public Writer() {
    }

    public Writer(Long id, String name, Set<Post> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }
}
