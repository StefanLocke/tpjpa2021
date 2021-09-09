package test.testjpa;

import test.testjpa.dao.testDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Service {

    private EntityManager manager;

    public Service() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("mysql");
        manager = factory.createEntityManager();
    }
    /*** TEACHER ACTIONS ***/
    public void teacherRegister(String name) {
        testDAO dao = new testDAO(manager);
        dao.createTeacher(name);
    }


    public void teacherCreateMeeting(String studentName, String teacherName, Date date) {
        testDAO dao = new testDAO(manager);
        if (dao.studentExists(studentName) && dao.teacherExists(teacherName)) {
            dao.createMeeting(dao.getStudentFromName(studentName), dao.getTeacherFromName(teacherName), date);
            System.out.println("Meeting Created");
        }else {
            System.out.println("Meeting not Created, student or teacher does not exist");
        }
    }

    /*** STUDENT ACTIONS ***/
    public void studentRegister(String name) {
        testDAO dao = new testDAO(manager);
        dao.createStudent(name);
    }
    public void studentCreateMeeting(String studentName, String teacherName, Date date) {
        testDAO dao = new testDAO(manager);
        if (dao.studentExists(studentName) && dao.teacherExists(teacherName)) {
            dao.createMeeting(dao.getStudentFromName(studentName), dao.getTeacherFromName(teacherName), date);
            System.out.println("Meeting Created");
        }else {
            System.out.println("Meeting not Created, student or teacher does not exist");
        }
    }
}
