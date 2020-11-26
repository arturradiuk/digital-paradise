package model.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Address {

    @Getter @Setter
    private String street;

    @Getter @Setter
    private String number;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public Address(String street, String number) {
        this.street = street;
        this.number = number;
    }
}
