package model.clients;

import controller.exceptions.ClientException;
import lombok.ToString;
import model.entities.Address;
import model.entities.Person;

@ToString(callSuper = true)
public class Administrator extends Person {
    public Administrator(String email, String name, Address address) throws ClientException {
        super(email, name, address);
    }
}
