package test.testjpa;

import test.testjpa.dao.DAO;
import test.testjpa.domain.Meeting;
import test.testjpa.domain.Pro;
import test.testjpa.domain.User;
import test.testjpa.jpa.JpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Service {

    private EntityManager manager;

    public Service() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("mysqlhome");
        manager = factory.createEntityManager();
    }




    /*** GLOBAL ACTIONS ***/

    public List<Meeting> viewAvailableTimeSlots(Pro pro){
        DAO dao = new DAO(manager);
        return dao.getAvailableTimeSlots(pro);
    }




    /*** TEACHER ACTIONS ***/
    public void proRegister(String name) {
        DAO dao = new DAO(manager);
        Pro pro = new Pro(name);
        dao.registerPro(pro);
    }

    public Meeting addTimeSlot(long proId, Date start, int duration){
        DAO dao = new DAO(manager);
        return dao.createTimeSlots(dao.getProFromId(proId),start,duration);
    }

    public void removeTimeSlot(long meetingId){
        DAO dao = new DAO(manager);
        dao.removeTimeSlots(dao.getMeetingFromId(meetingId));
    }




    /*** STUDENT ACTIONS ***/

    public void userRegister(String name) {
        DAO dao = new DAO(manager);
        User user = new User(name);
        dao.registerUser(user);
    }

    public void requestMeeting(long studentId, long meetingId){
        DAO dao = new DAO(manager);
        dao.joinTimeSlot(dao.getUserFromId(studentId),dao.getMeetingFromId(meetingId));
    }

    public void cancelMeeting(long meetingId){
        DAO dao = new DAO(manager);
        Meeting meeting = dao.getMeetingFromId(meetingId);
        meeting.setUser(null);
        dao.update(meeting);
    }





}
