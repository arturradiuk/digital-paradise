package controller.exceptions.good;

public class GoodException extends Exception {
    public static String NEGATIVE_PRICE = "Negative price";

    public GoodException(String message) {
        super(message);
    }
}
