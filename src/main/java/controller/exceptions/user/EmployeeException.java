package controller.exceptions.user;

public class EmployeeException extends UserException {
    public static String NULL_FIELD = "Null field in the constructor";
    public EmployeeException(String message) {
        super(message);
    }
}
