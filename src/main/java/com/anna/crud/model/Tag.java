package com.anna.crud.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID")
    private  Long id;
    @Column(name="NAME")
    private  String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag_post",
            joinColumns = { @JoinColumn(name = "ID_TAG") },
            inverseJoinColumns = { @JoinColumn(name = "ID_POST") })
    private Set<Post> postsSet = new HashSet<Post>();

    public Tag() {
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Post> getPostsSet() {
        return postsSet;
    }

    public void setPostsSet(Set<Post> postsSet) {
        this.postsSet = postsSet;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

