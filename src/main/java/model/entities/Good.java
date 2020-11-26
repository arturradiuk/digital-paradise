package model.entities;

import java.util.UUID;
import controller.exceptions.GoodException;
import lombok.Getter;
import lombok.Setter;

public abstract class Good {
    @Getter
    private final UUID uuid;

    @Setter
    private double basePrice;

    public Good(double basePrice) throws GoodException {
        if (basePrice < 0) {
            throw new GoodException("Price cannot be negative number.");
        }
        this.uuid = UUID.randomUUID();
        this.basePrice = basePrice;
    }

    protected double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "Good{" +
                "uuid=" + uuid +
                ", price=" + basePrice +
                '}';
    }
}
