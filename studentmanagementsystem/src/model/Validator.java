package model;

/**
 *
 * @author leonardo
 */
public class Validator {

    public static void validateNoDuplicatedCarnet(Student students[], 
            String carnet, short index) throws ValidationException {

        if (index >= students.length) {
            return;
        }

        if (students[index].getCarnet().equals(carnet)) {
            throw new ValidationException("Carnet " + carnet + " duplicado.");

        }
        validateNoDuplicatedCarnet(students, carnet, (short) (index + 1));
    }

    public static void validateNoEmptyCarnet(String carnet) throws 
            ValidationException {
        if (carnet == null || carnet.trim().isBlank()
                || carnet.trim().isEmpty()) {
            throw new ValidationException("El carnet no puede estar vacio.");
        }
    }

    public static void validateNoEmptyName(String name) throws 
            ValidationException {
        if (name == null || name.trim().isBlank()
                || name.trim().isEmpty()) {
            throw new ValidationException("El nombre no puede estar vacio.");
        }
    }

    public static void validateGradeRange(float grade) throws 
            ValidationException {
        if (grade < 0 || grade > 100) {
            throw new ValidationException
        ("Las calificaciones deben estar entre 0 y 100.");
        }
    }

    public static void validateRegsiteredsStudent(Student students[],
            String carnetToFind, short index) throws ValidationException {
        if (index >= students.length) {
            throw new ValidationException("Estudiante con carnet " 
                    + carnetToFind + " no encontrado.");
        }
        if (students[index] != null && students[index].getCarnet().
                equals(carnetToFind)) {
            return;
        }
        validateRegsiteredsStudent(students, carnetToFind, (short) (index + 1));
    }

}
