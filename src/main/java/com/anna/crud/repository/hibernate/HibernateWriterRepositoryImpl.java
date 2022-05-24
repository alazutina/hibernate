package com.anna.crud.repository.hibernate;

import com.anna.crud.model.Post;
import com.anna.crud.model.Writer;
import com.anna.crud.repository.WriterRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Set;

public class HibernateWriterRepositoryImpl implements WriterRepository {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    //5
    public Writer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Writer writer = (Writer) session.get(Writer.class,id) ;
           Set<Post> post  = writer.getPosts();
            transaction.commit();
            return writer;
        } catch (Exception e) {
            if (transaction != null) {
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    //1
    public  Writer save(Writer writer){
System.out.println(writer);
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(writer);//
                transaction.commit();
                return writer;
            } catch (Exception e) {
                if (transaction != null) {
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        return null;
    }

    //4
    public Writer update(Writer writer) {//    public void updateTag(long id, String name) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Writer writer1 = new Writer(writer.getId(),writer.getName(),writer.getPosts());
            session.update(writer1);
            transaction.commit();
            return writer1;
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
    public List<Writer> getAll() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Writer> writers = session.createQuery("from Writer").list();// session.createQuery("FROM tags").list();
            transaction.commit();
            return writers;
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
            Writer writer = (Writer) session.get(Writer.class, id);
            for(Post post: writer.getPosts()) {
                post.getTags().remove(writer);
                          }
            writer.getPosts().clear();
            session.delete(writer);
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
