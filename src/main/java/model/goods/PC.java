package model.goods;

import controller.exceptions.GoodException;

public class PC extends Computer {
    public PC() {
        super();
    }

    public PC(double basePrice,int count, int ram, int ssdCapacity) throws GoodException {
        super(basePrice, count, ram, ssdCapacity);
    }
}
