package model.goods;

import controller.exceptions.GoodException;
import lombok.Getter;

public class Laptop extends Computer {
    @Getter
    private double screenSize;
    @Getter
    private boolean hasCamera;
    @Getter
    private int usbAmount;

    public Laptop() {
        super();
    }

    public Laptop(double basePrice, int ram, int ssdCapacity, double screenSize, boolean hasCamera, int usbAmount) throws GoodException {
        super(basePrice, ram, ssdCapacity);
        if (screenSize < 0) {
            throw new GoodException("Screen size cannot be negative.");
        }
        if (usbAmount < 0) {
            throw new GoodException("USB amount cannot be negative.");
        }
        this.screenSize = screenSize;
        this.hasCamera = hasCamera;
        this.usbAmount = usbAmount;
    }
    
    public double getPrice() {
        return getBasePrice() + screenSize * 10 + usbAmount * 100 + (hasCamera ? 200 : 0);
    }
    
    @Override
    public String toString() {
        return super.toString() + " Laptop{" +
                "screenSize=" + screenSize +
                ", hasCamera=" + hasCamera +
                ", usbAmount=" + usbAmount +
                '}';
    }
}
