package test.testjpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Meeting {
    public Meeting(Pro pro,Date start, int duration) {
        this.start = start;
        this.pro = pro;
        this.duration = duration;
    }

    public Meeting() {
    }

    private long id;

    private Date start;

    private int duration;

    private User user;

    private Pro pro;

    @Id
    @GeneratedValue
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date date) {
        this.start = date;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    public Pro getPro() {
        return pro;
    }

    public void setPro(Pro pro) {
        this.pro = pro;
    }
}
