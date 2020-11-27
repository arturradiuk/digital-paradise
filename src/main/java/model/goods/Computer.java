package model.goods;

import controller.exceptions.GoodException;
import lombok.Getter;
import model.entities.Good;

public abstract class Computer extends Good {
    @Getter
    private int ram;
    @Getter
    private int ssdCapacity;

    public Computer() {
        super();
    }

    public Computer(double basePrice, int ram, int ssdCapacity) throws GoodException {
        super(basePrice);
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

    @Override
    public String toString() {
        return super.toString() + " Computer{" +
                "ram=" + ram +
                ", ssdCapacity=" + ssdCapacity +
                '}';
    }
}