package Model.Entity;

/**
 *
 * @author leonardo
 */
public class Course {

    private byte credits;
    private byte quota;
    private String code;
    private String name;

    public Course() {
    }

    public Course(byte credits, byte quota, String code, String name) {
        this.credits = credits;
        this.quota = quota;
        this.code = code;
        this.name = name;
    }

    public byte getCredits() {
        return credits;
    }

    public byte getQuota() {
        return quota;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Course{" + "credits=" + credits + ", quota=" + quota
                + ", code=" + code + ", name=" + name + '}';
    }

}
