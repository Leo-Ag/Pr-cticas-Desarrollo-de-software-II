package Model.Service;

import Model.Validation.ValidationService;
import Model.Entity.Course;
import Model.Entity.Enroll;
import Model.Entity.Student;
import Model.Excepcion.BussinessException;

public class RegisterService {

    // ==================== Arrays & Counters =====================
    private final Student[] students = new Student[100];
    private final Course[] courses = new Course[100];
    private final Enroll[] enrolls = new Enroll[100];

    private int counterStudent = 0;
    private int counterCourse = 0;
    private int counterEnroll = 0;

    // ==================== STUDENTS´ METHODS ====================
    public void registerStudent(String carnet, String nombre)
            throws BussinessException {

        ValidationService.validateCarnet(carnet);
        ValidationService.validateName(nombre);
        ValidationService.validateCapacity((byte) counterStudent, (byte) 100);
        ValidationService.validateNoDuplicatedCarnet(carnet, students, (byte) 0);

        Student student = new Student(carnet, nombre);
        students[counterStudent] = student;
        counterStudent++;
    }

    // ==================== COURSES´ METHODS ====================
    public void registerCourse(String code, String name, byte credits,
            byte quota) throws BussinessException {

        ValidationService.validateCourseCode(code);
        ValidationService.validateCourseName(name);
        ValidationService.validateCourseCredits(credits);
        ValidationService.validateCourseQuota(quota);
        ValidationService.validateCapacity((byte) counterCourse, (byte) 100);
        ValidationService.validateNoDuplicatedCourseCode(code, courses, (byte) 0);

        Course course = new Course(credits, quota, code, name);
        courses[counterCourse] = course;
        counterCourse++;
    }

    // ==================== ERROLL´S METHODS ====================
    public void enrollStudent(String carnet, String code)
            throws BussinessException {

        validateStudentExists(carnet);
        validateCourseExists(code);
        ValidationService.validateCapacity((byte) counterEnroll, (byte) 100);
        validateNoDuplicatedEnrollment(carnet, code);

        Student student = findStudentByCarnet(carnet);
        Course course = findCourseByCode(code);

        Enroll enroll = new Enroll(student, course);
        enrolls[counterEnroll] = enroll;
        counterEnroll++;
    }

    // ==================== SEARCHING´S METHODS ====================
    private Student findStudentByCarnet(String carnet) {
        return findStudentByCarnetRecursive(carnet, 0);  
    }

    private Student findStudentByCarnetRecursive(String carnet, int index) {
        if (index >= counterStudent) {
            return null;
        }
        if (students[index] != null && students[index].getCarnet().equals(carnet)) {
            return students[index];
        }
        return findStudentByCarnetRecursive(carnet, index + 1);
    }

    private Course findCourseByCode(String code) {
        return findCourseByCodeRecursive(code, 0);  
    }

    private Course findCourseByCodeRecursive(String code, int index) {
        if (index >= counterCourse) {
            return null;
        }
        if (courses[index] != null && courses[index].getCode().equals(code)) {
            return courses[index];
        }
        return findCourseByCodeRecursive(code, index + 1);
    }

    // ==================== SPECIFIC VALIDATIONS  ====================
    private void validateStudentExists(String carnet) throws BussinessException {
        if (findStudentByCarnet(carnet) == null) {
            throw new BussinessException("Estudiante con carnet " + carnet + " no existe.");
        }
    }

    private void validateCourseExists(String code) throws BussinessException {
        if (findCourseByCode(code) == null) {
            throw new BussinessException("Curso con código " + code + " no existe.");
        }
    }

    private void validateNoDuplicatedEnrollment(String carnet, String courseCode)
            throws BussinessException {
        for (int i = 0; i < counterEnroll; i++) {
            Enroll enroll = enrolls[i];
            if (enroll.getStudent().getCarnet().equals(carnet)
                    && enroll.getCourse().getCode().equals(courseCode)) {
                throw new BussinessException("El estudiante ya está matriculado en este curso.");
            }
        }
    }

    // ==================== LIST´S METHODS ====================
    public String[] listAllStudents() {
        String[] lista = new String[counterStudent];
        fillStudents(lista, 0);  // Empieza desde índice 0
        return lista;
    }

    private void fillStudents(String[] lista, int index) {

        if (index >= counterStudent) {
            return;
        }

        lista[index] = students[index].toString();

        fillStudents(lista, index + 1);
    }

    public String[] listAllCourses() {
        String[] lista = new String[counterCourse];
        fillCourses(lista, 0);
        return lista;
    }

    private void fillCourses(String[] lista, int index) {
        if (index >= counterCourse) {
            return;
        }

        lista[index] = courses[index].toString();
        fillCourses(lista, index + 1);
    }

    public String[] listAllEnrollments() {
        String[] lista = new String[counterEnroll];
        fillEnrollments(lista, 0);
        return lista;
    }

    private void fillEnrollments(String[] lista, int index) {
        if (index >= counterEnroll) {
            return;
        }

        lista[index] = enrolls[index].toString();
        fillEnrollments(lista, index + 1);
    }

    public String[] listEnrollmentsByStudent(String carnet) {

        int count = countEnrollmentsRecursive(carnet, 0);

        String[] resultado = new String[count];
        fillEnrollmentsByStudentRecursive(carnet, resultado, 0, 0);

        return resultado;
    }

    private int countEnrollmentsRecursive(String carnet, int index) {
        if (index >= counterEnroll) {
            return 0;
        }

        int count = 0;
        if (enrolls[index].getStudent().getCarnet().equals(carnet)) {
            count = 1;
        }

        return count + countEnrollmentsRecursive(carnet, index + 1);
    }

    private void fillEnrollmentsByStudentRecursive(String carnet, String[] resultado,
            int sourceIndex, int destIndex) {
        if (sourceIndex >= counterEnroll) {
            return;
        }

        if (enrolls[sourceIndex].getStudent().getCarnet().equals(carnet)) {
            resultado[destIndex] = enrolls[sourceIndex].toString();
            fillEnrollmentsByStudentRecursive(carnet, resultado, sourceIndex + 1, destIndex + 1);
        } else {
            fillEnrollmentsByStudentRecursive(carnet, resultado, sourceIndex + 1, destIndex);
        }
    }

    // ==================== GETTERS FOR CONTROLLER ====================
    public Student[] getStudents() {
        Student[] lista = new Student[counterStudent];
        System.arraycopy(students, 0, lista, 0, counterStudent);
        return lista;
    }

    public Course[] getCourses() {
        Course[] lista = new Course[counterCourse];
        System.arraycopy(courses, 0, lista, 0, counterCourse);
        return lista;
    }

    public Enroll[] getEnrolls() {
        Enroll[] lista = new Enroll[counterEnroll];
        System.arraycopy(enrolls, 0, lista, 0, counterEnroll);
        return lista;
    }

    public int getStudentCount() {
        return counterStudent;
    }

    public int getCourseCount() {
        return counterCourse;
    }

    public int getEnrollmentCount() {
        return counterEnroll;
    }
}
