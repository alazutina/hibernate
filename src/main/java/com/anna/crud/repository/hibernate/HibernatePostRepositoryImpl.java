package com.anna.crud.repository.hibernate;

import com.anna.crud.model.Post;
import com.anna.crud.model.Tag;
import com.anna.crud.model.Writer;
import com.anna.crud.repository.PostRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class HibernatePostRepositoryImpl implements PostRepository {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    //5
    public Post getById(Long id) {
        Transaction transaction = null;
        try (     Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Post p Left Join FETCH p.tags where p.id = "+ id , Post.class);
            Post post = (Post) query.getSingleResult();
            transaction.commit();
            return post;
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

    //1
public  Post save(Post post){
        if (post != null) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
        transaction = session.beginTransaction();
        session.save(post);//
        transaction.commit();
        return post;
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
        e.printStackTrace();
        }
        }
        return null;
        }

//4
    public Post update(Post post) {//    public void updateTag(long id, String name) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Post post1 = new Post(post.getId(), post.getContent(),post.getTags(),post.getStatus());
            session.update(post1);
            transaction.commit();
            return post1;
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

//3
    public  List<Post> getAll() {
        Transaction transaction = null;
        try(Session session = this.sessionFactory.openSession()){
            transaction =  session.beginTransaction();
            Query query = session.createQuery("from Post p Left Join FETCH p.tags ", Post.class);
            List<Post> postsList = query.getResultList();
            transaction.commit();
            return postsList;
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

//2
    public void deleteById(Long id) {
        Transaction transaction = null;
        try(Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Post post = (Post) session.get(Post.class, id);
            for(Tag t: post.getTags()) {
                t.getPostsSet().remove(post);
            }
            post.getTags().clear();
            session.delete(post);
            transaction.commit();
                }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
    }
}