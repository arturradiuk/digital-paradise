package model.goods;

import controller.exceptions.good.ComputerException;
import controller.exceptions.good.GoodException;
import lombok.Data;
import lombok.ToString;
import model.entities.Good;

@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Computer extends Good {
    private int ram;
    private int ssdCapacity;

    public Computer() {
        super();
    }

    public Computer(double basePrice, String goodName, int ram, int ssdCapacity, int count) throws GoodException {
        super(basePrice, goodName, count);
        if (ram < 0) {
            throw new ComputerException(ComputerException.NEGATIVE_RAM);
        }
        if (ssdCapacity < 0) {
            throw new ComputerException(ComputerException.NEGATIVE_SSD);
        }
        this.ram = ram;
        this.ssdCapacity = ssdCapacity;
    }

    @Override
    public double getBasePrice() {
        return super.getBasePrice() + (this.ram / 1024.0) * 50 + (ssdCapacity / 1024.0) * 150;
    }


}