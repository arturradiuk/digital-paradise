package model.entities;

import java.util.Objects;
import java.util.UUID;

import controller.exceptions.GoodException;
import lombok.*;

@ToString(callSuper = true)
@Data
@NoArgsConstructor
public abstract class Good {

    private String goodName;

    private UUID uuid = UUID.randomUUID();

    private double basePrice;

    private int count; // todo remove this attribute


    public Good(double basePrice, int count, String goodName) throws GoodException {
        if (basePrice < 0) {
            throw new GoodException("Price cannot be negative number.");
        }
        if (count < 0) {
            throw new GoodException("Count cannot be negative number.");
        }
        this.basePrice = basePrice;
        this.count = count;
        this.goodName = goodName;
    }

    protected double getBasePrice() {
        return basePrice;
    }

}
