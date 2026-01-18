package model;

public class Student {

    private String name;

    private String carnet;

    private float[] grades;

    public Student() {
    }

    public Student(String name, String carnet, float[] grades) {
        this.name = name;
        this.carnet = carnet;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public String getCarnet() {
        return carnet;
    }

    public float[] getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", carnet=" + carnet + ", grades=" + grades + '}';
    }
    
}
