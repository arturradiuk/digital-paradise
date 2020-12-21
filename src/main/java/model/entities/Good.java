package model.entities;

import java.util.Objects;
import java.util.UUID;

import controller.exceptions.good.GoodException;
import lombok.*;

@ToString(callSuper = true)
@Data
@NoArgsConstructor
public abstract class Good {

    private UUID uuid = UUID.randomUUID();

    private boolean sold = false;


    private String goodName;

    private double basePrice;

    private int count;


    public Good(double basePrice, String goodName, int count) throws GoodException {
        if (basePrice < 0) {
            throw new GoodException(GoodException.NEGATIVE_PRICE);
        }
        this.basePrice = basePrice;
        this.goodName = goodName;
        this.count = count;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public boolean equals(Object o) { // todo remember
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return uuid.equals(good.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
