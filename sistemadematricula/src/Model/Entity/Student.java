package Model.Entity;

/**
 *
 * @author leonardo
 */
public class Student {

    private String carnet;
    private String name;

    public Student() {
    }

    public Student(String carnet, String name) {
        this.carnet = carnet;
        this.name = name;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" + "carnet=" + carnet + ", name=" + name + '}';
    }

}
