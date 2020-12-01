package model.goods;

import controller.exceptions.GoodException;
import lombok.ToString;

@ToString(callSuper = true)
public class PC extends Computer {
    public PC() {
        super();
    }

    public PC(double basePrice, int count, String goodName, int ram, int ssdCapacity) throws GoodException {
        super(basePrice, count, goodName, ram, ssdCapacity);
    }
}
