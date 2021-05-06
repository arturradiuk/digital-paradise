package controller.exceptions.controller;

public class GoodControllerException extends Exception{
    public static String NUMBER_OF_GOODS_CANNOT_BE_ZERO_AND_NAME_CANNOT_NULL = "Number of goods can't be zero and good name can't be empty";
    public GoodControllerException(String message) {
        super(message);
    }
}
