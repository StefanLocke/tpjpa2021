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
                Persistence.createEntityManagerFactory("dev");
        manager = factory.createEntityManager();
    }




    /*** GLOBAL ACTIONS ***/

    public List<Meeting> viewAvailableTimeSlots(Pro pro){
        DAO dao = new DAO(manager);
        return dao.getAvailableTimeSlots(pro);
    }

    public void cancelMeeting(long meetingId){

    }

    public List<User> getUsers(){
        DAO dao = new DAO(manager);
        return dao.getAllUsers();
    }

    public List<Pro> getPro(){
        DAO dao = new DAO(manager);
        return dao.getAllPros();
    }

    /*** TEACHER ACTIONS ***/
    public void proRegister(Pro pro) {
        DAO dao = new DAO(manager);
        dao.registerPro(pro);
    }

    public Meeting addTimeSlot(Pro pro, Date start, int duration){
        DAO dao = new DAO(manager);
        return dao.createTimeSlots(pro,start,duration);
    }

    public void removeTimeSlot(Meeting meeting){
        DAO dao = new DAO(manager);
        dao.removeTimeSlots(meeting);
    }




    /*** STUDENT ACTIONS ***/

    public void userRegister(User user) {
        DAO dao = new DAO(manager);
        dao.registerUser(user);
    }

    public void requestMeeting(User user, Meeting meeting){
        DAO dao = new DAO(manager);
        dao.joinTimeSlot(user,meeting);
    }

    public void cancelMeeting(String studentName,long meetingId){

    }



    public static void main(String[] args) {

        Service service = new Service();
        User user = new User("Steve");
        Pro pro = new Pro("Bob Ross");
        service.userRegister(user);
        service.proRegister(pro);

        Meeting meeting = service.addTimeSlot(pro,Date.from(Instant.now()),15);

        service.requestMeeting(user,meeting);

        System.out.println(".. done");
    }

}
