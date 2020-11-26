package model.goods;

import java.util.UUID;
import lombok.Getter;
import model.entities.Good;

public abstract class Computer extends Good {
    @Getter
    private final int ram;
    @Getter
    private final int ssdCapacity;
    @Getter
    private final UUID uuid;

    public Computer(double price, int ram, int ssdCapacity) {
        super(price);
        this.uuid = UUID.randomUUID();
        this.ram = ram;
        this.ssdCapacity = ssdCapacity;
    }
}