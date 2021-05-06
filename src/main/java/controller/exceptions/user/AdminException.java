package controller.exceptions.user;

public class AdminException extends UserException {
    public static String NULL_FIELD = "Null field in the constructor";

    public AdminException(String message) {
        super(message);
    }
}
