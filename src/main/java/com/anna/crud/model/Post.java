package com.anna.crud.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private  Long id;

    @Column(name="CONTENT")
    private  String content;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_post",
            joinColumns = { @JoinColumn(name = "ID_POST") },
            inverseJoinColumns = { @JoinColumn(name = "ID_TAG") })
    private Set<Tag> tags = new HashSet<Tag>();

    @Column(name="STATUS")
     private String status;

    public Post() {
    }

    public Post(Long id, String content, Set<Tag> tags, String status) {
        this.id = id;
        this.content = content;
        this.tags = tags;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", status=" + status +
                '}';
    }
}
