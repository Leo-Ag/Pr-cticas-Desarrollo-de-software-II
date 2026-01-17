package model;


import model.StudentManager;
import model.ExceptionServer;

public class ValidationException extends ExceptionServer {

    public String validateStudentExists(StudentManager manager, String carnet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String validateNoDuplicatedCarnet(StudentManager manager, String carnet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String validateNoEmptyCarnet(String carnet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String validateNoEmptyName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void validateGradeRange() {
    }

    public void untitledMethod() {
    }
}
