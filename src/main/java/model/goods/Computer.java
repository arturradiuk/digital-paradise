package model.goods;

import controller.exceptions.GoodException;
import lombok.Getter;
import lombok.ToString;
import model.entities.Good;

@ToString(callSuper = true)
public abstract class Computer extends Good {
    @Getter
    private int ram;
    @Getter
    private int ssdCapacity;

    public Computer() {
        super();
    }

    public Computer(double basePrice, int count, String goodName, int ram, int ssdCapacity) throws GoodException {
        super(basePrice,count,goodName);
        if (ram < 0) {
            throw new GoodException("RAM amount cannot be negative.");
        }
        if (ssdCapacity < 0) {
            throw new GoodException("SSD disk capacity cannot be negative.");
        }
        this.ram = ram;
        this.ssdCapacity = ssdCapacity;
    }

    @Override
    protected double getBasePrice() {
        return super.getBasePrice() + (this.ram / 1024.0) * 50 + (ssdCapacity / 1024.0) * 150;
    }


}