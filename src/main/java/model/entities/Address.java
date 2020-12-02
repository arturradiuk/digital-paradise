package model.entities;

import controller.exceptions.AddressException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Address {

    private String street;

    private String number;

    public Address() {
    }

    public Address(String street, String number) throws AddressException {
        if(street.equals(""))
            throw new AddressException("Street field is not set");
        if(number.equals(""))
            throw new AddressException("Number field is not set");
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return
                "street='" + street + '\'' +
                ", number='" + number + '\'';
    }
}
