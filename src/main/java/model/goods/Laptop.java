package model.goods;

import controller.exceptions.GoodException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Laptop extends Computer {
    private double screenSize;
    private boolean hasCamera;
    private int usbAmount;

    public Laptop() {
        super();
    }

    public Laptop(double basePrice, String goodName, int ram, int ssdCapacity, double screenSize, boolean hasCamera, int usbAmount, int count) throws GoodException {
        super(basePrice, goodName, ram, ssdCapacity, count);
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


}
