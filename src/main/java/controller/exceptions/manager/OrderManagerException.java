package controller.exceptions.manager;

public class OrderManagerException extends Exception{
    public static String NOT_ENOUGH_GOODS = "There are not enough goods in magazine";
    public OrderManagerException(String message){super(message);}
}
