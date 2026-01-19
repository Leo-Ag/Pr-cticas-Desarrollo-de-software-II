package model;

/**
 * Custom exception type for all validation failures in the student management system.
 * Provides consistent error handling with meaningful Spanish messages for end users.
 */
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }

}
