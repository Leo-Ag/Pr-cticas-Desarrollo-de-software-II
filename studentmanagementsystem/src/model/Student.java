package model;

/**
 * Core data container for student information. Follows the DTO (Data Transfer
 * Object) pattern to transfer data between layers. Contains only the essential
 * student attributes with no business logic.
 */
public class Student {

    private String name;
    private String carnet;
    private float[] grades;

    /**
     * Constructs a Student with their basic information and academic grades.
     *
     * @param name The student's full name
     * @param carnet The unique institutional identification number
     * @param grades Array of exactly 3 course grades (values 0-100)
     */
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
        return "Student{" + "name=" + name + ", carnet=" + carnet + ", grades="
                + grades + '}';
    }
    
    
}
