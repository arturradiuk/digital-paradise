package model.entities;

import controller.exceptions.AddressException;
import lombok.Getter;
import lombok.Setter;

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

    public Address(String street, String number) throws AddressException {
        if(street.equals(""))
            throw new AddressException("Street field is not set");
        if(number.equals(""))
            throw new AddressException("Number field is not set");
        this.street = street;
        this.number = number;
    }
}
