package test.testjpa.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import test.testjpa.dao.testDAO;
import test.testjpa.domain.Student;
import test.testjpa.domain.Teacher;

import java.sql.Date;
import java.time.Instant;

public class JpaTest {

    private EntityManager manager;
    private testDAO dao;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
        dao = new testDAO(manager);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("mysql");
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
        dao.createMeeting(dao.getStudentFromName("Steve"),dao.getTeacherFromName("Bob Ross"), Date.from(Instant.now()));
        tx.commit();
        manager.close();
    }

    public void fillStudents() {
        int studentCount = manager.createQuery("Select a from Student a").getResultList().size();
        if (studentCount == 0 ) {
            manager.persist(new Student("Steve"));
            manager.persist(new Student("Matt"));
            manager.persist(new Student("Rob"));
            manager.persist(new Student("John"));
            manager.persist(new Student("Billy"));
            manager.persist(new Student("Adam"));
        }
    }

    public void fillTeachers() {
        int teacherCount = manager.createQuery("Select a from Teacher a").getResultList().size();
        if (teacherCount == 0 ) {
            manager.persist(new Teacher("Bob Ross"));
            manager.persist(new Teacher("Steven Derrien"));
            manager.persist(new Teacher("Simon Rockiki"));
        }
    }

}
