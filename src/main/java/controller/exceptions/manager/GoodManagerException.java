package controller.exceptions.manager;

public class GoodManagerException extends Exception{
    public static String CANNON_DELETE = "Cannot delete the good that is a part of order.";
    public GoodManagerException(String message) {
        super(message);
    }
}
