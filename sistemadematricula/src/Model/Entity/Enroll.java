package Model.Entity;

import java.util.Date;

public class Enroll {

    private final Date date;
    private final Course course;
    private final Student student;

    public Enroll(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.date = new Date();
    }

    public Enroll(Date date, Course course, Student student) {
        this.date = date;
        this.course = course;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return student.getCarnet() + " matriculado en " + course.getCode()
                + " - Fecha: " + date;
    }
}
