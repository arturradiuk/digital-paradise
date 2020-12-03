package model.goods;

import controller.exceptions.GoodException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import model.entities.Good;

import java.util.Objects;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Computer extends Good {
    private int ram;
    private int ssdCapacity;

    public Computer() {
        super();
    }

    public Computer(double basePrice, String goodName, int ram, int ssdCapacity) throws GoodException {
        super(basePrice,goodName);
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
    public double getBasePrice() {
        return super.getBasePrice() + (this.ram / 1024.0) * 50 + (ssdCapacity / 1024.0) * 150;
    }

}