package model.entities;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

public abstract class Good {
    @Getter
    private final UUID uuid;
    @Getter
    @Setter
    private double price;

    public Good(double price) {
        this.uuid = UUID.randomUUID();
        this.price = price;
    }
}
