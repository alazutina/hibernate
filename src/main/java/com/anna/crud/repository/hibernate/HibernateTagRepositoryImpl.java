package com.anna.crud.repository.hibernate;

import com.anna.crud.model.Tag;
import com.anna.crud.repository.TagRepository;
import java.util.List;
import com.anna.crud.model.Post;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTagRepositoryImpl implements TagRepository  {

    private static SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();

        public Tag getById(Long id) {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Tag tag = (Tag) session.get(Tag.class, id);
                transaction.commit();
                return tag;
            }
            catch (Exception e){
                if(transaction !=null){}
                e.printStackTrace();
            }finally {
                session.close();
            }
            return null;
             }

//3
        public Tag save(Tag tag1){    // public void addTag(long id, String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Tag tag = new Tag(tag1.getId(), tag1.getName());
            session.save(tag);//    session.save(tag);
            transaction.commit();
        return  tag;}
        catch (Exception e){
            if(transaction !=null){}
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }


    //4
        public Tag update(Tag t) {//    public void updateTag(long id, String name) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Tag tag = (Tag) session.get(Tag.class, t.getId());
            tag.setName(t.getName());
            session.update(tag);
            transaction.commit();
            return tag;
        }
        catch (Exception e){
            if(transaction !=null){}
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }


//1
    public List<Tag> getAll() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Tag> tagList = session.createQuery("from Tag").list();// session.createQuery("FROM tags").list();
            transaction.commit();
            return tagList;
        }
        catch (Exception e){
            if(transaction !=null){}
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

//2
        public void deleteById(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Tag tag = (Tag) session.get(Tag.class, id);
             for(Post p: tag.getPostsSet()) {
                p.getTags().remove(tag);
            }
            tag.getPostsSet().clear();
            session.delete(tag);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction !=null){}
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    }


