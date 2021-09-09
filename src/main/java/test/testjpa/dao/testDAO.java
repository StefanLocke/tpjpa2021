package test.testjpa.dao;

import test.testjpa.domain.Meeting;
import test.testjpa.domain.Student;
import test.testjpa.domain.Teacher;

import javax.management.ListenerNotFoundException;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class testDAO {

    private EntityManager manager;

    public testDAO(EntityManager manager) {
        this.manager = manager;
    }
    /*** MEETINGS ***/
    public void createMeeting(Student student,Teacher teacher, Date date) {
        manager.persist(new Meeting(date,student,teacher));
    }

    public List<Meeting> getMeetingsFromStudentName(String name) {

        return manager.createQuery("Select a From Meeting a Where a.student.name =\'" + name + "\'").getResultList();

    }

    public List<Meeting> getMeetingsFromTeacherName(String name) {
        return manager.createQuery("Select a From Meeting a Where a.teacher.name =\'" + name + "\'").getResultList();
    }



    /*** STUDENTS ***/
    public void createStudent(String name) {
        manager.persist(new Student(name));
    }

    public boolean studentExists(String name) {
        List list = manager.createQuery("Select a From Student a Where a.name =\'" + name + "\'").getResultList();
        return list.size() > 0;
    }
    public Student getStudentFromName(String name) {
        List list = manager.createQuery("Select a From Student a Where a.name =\'" + name + "\'").getResultList();
        if (list.size() > 0) {
            return (Student)list.get(0);
        }else {
            return null;
        }
    }

    public Student getStudentFromId(long id) {
        return null;
    }


    /*** TEACHERS ***/
    public void createTeacher(String name) {
        manager.persist(new Teacher(name));
    }
    public boolean teacherExists(String name) {
        List list = manager.createQuery("Select a From Teacher a Where a.name =\'" + name + "\'").getResultList();
        return list.size() > 0;
    }
    public Teacher getTeacherFromName(String name) {
        List list = manager.createQuery("Select a From Teacher a Where a.name =\'" + name + "\'").getResultList();
        if (list.size() > 0) {
            return (Teacher)list.get(0);
        }else {
            return null;
        }
    }








}