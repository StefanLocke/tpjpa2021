package test.testjpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Meeting {
    public Meeting(Date date1, Student student, Teacher teacher) {
        this.date1 = date1;
        this.student = student;
        this.teacher = teacher;
    }

    public Meeting() {
    }

    private long id;

    private Date date1;

    private Student student;

    private Teacher teacher;

    @Id
    @GeneratedValue
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date) {
        this.date1 = date;
    }

    @OneToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToOne
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
