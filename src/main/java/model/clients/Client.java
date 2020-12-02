package model.clients;

import controller.exceptions.ClientException;
import lombok.ToString;
import model.entities.Address;
import model.entities.User;

@ToString(callSuper = true)
public class Client extends User {
    public Client(String email, String name, Address address) throws ClientException {
        super(email, name, address);
    }
}
