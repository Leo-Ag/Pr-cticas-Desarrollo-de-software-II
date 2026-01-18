package Model.Validation;

import Model.Entity.Course;
import Model.Entity.Student;
import Model.Excepcion.BussinessException;

public class ValidationService {

    // ==================== STUDENTS VALIDATIONS ====================
    public static void validateCarnet(String carnet) throws BussinessException {
        if (carnet == null || carnet.trim().isEmpty()) {
            throw new BussinessException("El carnet no puede estar vacío.");
        }
    }

    public static void validateNoDuplicatedCarnet(String carnet, Student[] students, byte index)
            throws BussinessException {
        if (index >= students.length || students[index] == null) {
            return;
        }
        if (students[index].getCarnet().equals(carnet)) {
            throw new BussinessException("Carnet " + carnet + " duplicado.");
        }
        validateNoDuplicatedCarnet(carnet, students, (byte) (index + 1));
    }

    public static void validateName(String nombre) throws BussinessException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BussinessException("El nombre no puede estar vacío.");
        }
    }

    // ==================== COURSES VALIDATIONS ====================
    public static void validateCourseCode(String codigo) throws BussinessException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new BussinessException("El código del curso no puede estar vacío.");
        }
    }

    public static void validateCourseName(String nombre) throws BussinessException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BussinessException("El nombre del curso no puede estar vacío.");
        }
    }

    public static void validateNoDuplicatedCourseCode(String codigo, Course[] courses, byte index) throws BussinessException {

        if (index >= courses.length || courses[index] == null) {
            return;
        }
        if (courses[index].getCode().equals(codigo)) {
            throw new BussinessException("Código de curso " + codigo
                    + " ya existe.");
        }
        validateNoDuplicatedCourseCode(codigo, courses, (byte) (index + 1));
    }

    public static void validateCourseCredits(byte credits)
            throws BussinessException {

        if (credits <= 0) {
            throw new BussinessException("Los créditos deben ser mayores a 0.");
        }
    }

    public static void validateCourseQuota(int cupo) throws BussinessException {
        if (cupo <= 0) {
            throw new BussinessException("El cupo debe ser mayor a 0.");
        }
    }

    // ==================== ENROLL VALIDATIONS ====================
public static void validateCourseExistsRecursive(String codigoCurso, Course[] 
        courses, byte quantity, byte index) throws BussinessException {
   
    if (index >= quantity) {
        throw new BussinessException("Curso con código " + codigoCurso + 
                " no existe.");
    }
    
    if (courses[index] != null && courses[index].getCode().equals(codigoCurso)){
        return;  
    }
    
    validateCourseExistsRecursive(codigoCurso, courses, quantity, 
            (byte) (index + 1));
}

    // ==================== GENERAL VALIDATIONS ====================
    public static void validateCapacity(byte currentQuantity, byte maxQuantity)
            throws BussinessException {
        if (currentQuantity >= maxQuantity) {
            throw new BussinessException("Capacidad máxima alcanzada.");
        }
    }
}
