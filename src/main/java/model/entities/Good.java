package model.entities;

import java.util.Objects;
import java.util.UUID;

import controller.exceptions.GoodException;
import lombok.Getter;
import lombok.Setter;

public abstract class Good {
    @Getter
    private final UUID uuid;

    @Setter
    @Getter
    private double basePrice;

    @Setter
    @Getter
    private int count; // todo remove this attribute

    public Good() {
        this.uuid = UUID.randomUUID();
    }

    public Good(double basePrice, int count) throws GoodException {
        if (basePrice < 0) {
            throw new GoodException("Price cannot be negative number.");
        }
        if (count < 0) {
            throw new GoodException("Count cannot be negative number.");
        }
        this.uuid = UUID.randomUUID();
        this.basePrice = basePrice;
        this.count = count;
    }

    protected double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "Good{" +
                "uuid=" + uuid +
                ", basePrice=" + basePrice +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Good good = (Good) o;

        return uuid.equals(good.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
