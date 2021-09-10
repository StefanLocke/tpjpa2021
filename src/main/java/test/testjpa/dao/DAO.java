package test.testjpa.dao;

import test.testjpa.domain.Meeting;
import test.testjpa.domain.User;
import test.testjpa.domain.Pro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class DAO {

    private EntityManager manager;
    private EntityTransaction tx;
    public DAO(EntityManager manager) {
        tx = manager.getTransaction();
        this.manager = manager;
    }

    /*** MEETINGS ***/

    public Meeting createTimeSlots(Pro pro, Date start, int duration){
        tx.begin();
        Meeting meeting = new Meeting(pro,start,duration);
        manager.persist(meeting);
        tx.commit();
        return meeting;
    }

    public void removeTimeSlots(Meeting meeting){

    }

    public List<Meeting> getAvailableTimeSlots(Pro pro){
        List list = manager.createQuery("select a from Meeting a where a.user is null and a.pro.id = \'"+pro.getId()+"\'" ).getResultList();
        return list;
    }

    public void joinTimeSlot(User user, Meeting meeting){
        tx.begin();
        Meeting meeting1 = (Meeting) manager.createQuery("Select a from Meeting a where a.id =\'"+meeting.getId()+"\'").getResultList().get(0);
        meeting1.setUser(user);
        manager.persist(meeting1);
        tx.commit();
    }
    public void leaveTimeSlot(Meeting meeting){

    }

    public List<User> getAllUsers(){
        List list = manager.createQuery("SELECT a from User a").getResultList();
        return list;
    }

    public List<Pro> getAllPros(){
        List list = manager.createQuery("SELECT a from Pro a").getResultList();
        return list;
    }


    /*** STUDENTS ***/
    public void registerUser(User user) {
        tx.begin();
        manager.persist(user);
        tx.commit();
    }

    public boolean userExists(String name) {
        List list = manager.createQuery("Select a From User a Where a.name =\'" + name + "\'").getResultList();
        return list.size() > 0;
    }





    /*** TEACHERS ***/
    public void registerPro(Pro pro) {
        tx.begin();
        manager.persist(pro);
        tx.commit();
    }

    public boolean proExists(String name) {
        List list = manager.createQuery("Select a From Pro a Where a.name =\'" + name + "\'").getResultList();
        return list.size() > 0;
    }










}