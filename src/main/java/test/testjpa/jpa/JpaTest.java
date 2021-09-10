package test.testjpa.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import test.testjpa.dao.DAO;
import test.testjpa.domain.User;
import test.testjpa.domain.Pro;

import java.sql.Date;
import java.time.Instant;

public class JpaTest {

    private EntityManager manager;
    private DAO dao;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
        dao = new DAO(manager);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        JpaTest test = new JpaTest(manager);
        test.test();
        System.out.println(".. done");
    }

    public void test() {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            fillStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        tx = manager.getTransaction();
        tx.begin();
        try {
            fillTeachers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        tx.begin();
        tx.commit();
        manager.close();
    }

    public void fillStudents() {
        int studentCount = manager.createQuery("Select a from User a").getResultList().size();
        if (studentCount == 0 ) {
            manager.persist(new User("Steve"));
            manager.persist(new User("Matt"));
            manager.persist(new User("Rob"));
            manager.persist(new User("John"));
            manager.persist(new User("Billy"));
            manager.persist(new User("Adam"));
        }
    }

    public void fillTeachers() {
        int teacherCount = manager.createQuery("Select a from Pro a").getResultList().size();
        if (teacherCount == 0 ) {
            manager.persist(new Pro("Bob Ross"));
            manager.persist(new Pro("Steven Derrien"));
            manager.persist(new Pro("Simon Rockiki"));
        }
    }

}
