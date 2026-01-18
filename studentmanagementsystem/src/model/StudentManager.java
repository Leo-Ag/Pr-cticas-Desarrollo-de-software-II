package model;


import model.Student;

public class StudentManager {

    public StudentManager() {
    }

    private Student[] students;

    private short counter;

    public Student addStudent(Student student) {
        students[counter] = student;
        counter++;
        return student;
    }

    public void updateGrade(String carnet, float grade) {
        
    }

    public String deleteStudent(String carnet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void orderByAverage() {
    }

    public String findByCarnet(String carnet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float calculateAverage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Student[] getTopThree() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Student[] getAllStudents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
