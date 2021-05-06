package controller.exceptions.controller;

public class UserControllerException extends Exception{
    public static String NAME_CANNOT_NULL = "Good name can't be empty";
    public UserControllerException(String message) {
        super(message);
    }
}
