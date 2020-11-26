package model.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Good {
    @Getter
    private UUID uuid;
    @Getter @Setter
    private double price;

    public Good() {
        this.uuid = UUID.randomUUID();
    }

    public Good(double price) {
        this.uuid = UUID.randomUUID();
        this.price = price;
    }
}
