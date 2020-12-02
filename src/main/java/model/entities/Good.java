package model.entities;

import java.util.UUID;

import controller.exceptions.GoodException;
import lombok.*;

@ToString(callSuper = true)
@Data
@NoArgsConstructor
public abstract class Good {

    private UUID uuid = UUID.randomUUID();

    private boolean sold= false;

    private String goodName;

    private double basePrice;


    public Good(double basePrice, String goodName) throws GoodException {
        if (basePrice < 0) {
            throw new GoodException("Price cannot be negative number.");
        }
        this.basePrice = basePrice;
        this.goodName = goodName;
    }

    public double getBasePrice() {
        return basePrice;
    }

}
