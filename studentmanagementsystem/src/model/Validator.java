package model;

/**
 * Centralized validation service with recursive implementations.
 * All methods are static since validation logic is stateless and reusable.
 * Throws ValidationException with descriptive messages for any rule violation.
 */
public class Validator {

    /**
     * Ensures no student with the same carnet already exists.
     * Critical for maintaining data integrity in addStudent operations.
     * Recursively checks entire active student array.
     */
    public static void validateNoDuplicatedCarnet(Student students[],
            String carnet, short index, short activeCount) throws ValidationException {

        if (index >= activeCount) {
            return;
        }

        if (students[index] != null && students[index].getCarnet().equals(carnet)) {
            throw new ValidationException("Carnet " + carnet + " duplicado.");
        }

        validateNoDuplicatedCarnet(students, carnet, (short) (index + 1), activeCount);
    }

    /**
     * Validates that a carnet is not null, empty, or blank.
     * Basic input validation for any student registration or search.
     */
    public static void validateNoEmptyCarnet(String carnet) throws ValidationException {
        if (carnet == null || carnet.trim().isBlank() || carnet.trim().isEmpty()) {
            throw new ValidationException("El carnet no puede estar vacio.");
        }
    }

    /**
     * Validates that a name is not null, empty, or blank.
     * Ensures meaningful student identification in the system.
     */
    public static void validateNoEmptyName(String name) throws ValidationException {
        if (name == null || name.trim().isBlank() || name.trim().isEmpty()) {
            throw new ValidationException("El nombre no puede estar vacio.");
        }
    }

    /**
     * Ensures grades fall within the valid academic range (0-100).
     * Applied when adding new students or updating existing grades.
     */
    public static void validateGradeRange(float grade) throws ValidationException {
        if (grade < 0 || grade > 100) {
            throw new ValidationException("Las calificaciones deben estar entre 0 y 100.");
        }
    }

    /**
     * Confirms a student exists before operations like delete or update.
     * Recursively searches for the carnet; throws if not found.
     * Prevents operations on non-existent students.
     */
    public static void validateRegisteredStudent(Student students[],
            String carnetToFind, short index, short activeCount) throws ValidationException {

        if (index >= activeCount) {
            throw new ValidationException("Estudiante con carnet "
                    + carnetToFind + " no encontrado.");
        }

        if (students[index] != null && students[index].getCarnet().equals(carnetToFind)) {
            return;
        }

        validateRegisteredStudent(students, carnetToFind,
                (short) (index + 1), activeCount);
    }
}