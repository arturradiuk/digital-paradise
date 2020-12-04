package model.entities;

import controller.exceptions.ClientException;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class User {
    private UUID uuid = UUID.randomUUID();

    private String email;

    private String name;

    private Address address;


    public User(String email, String name, Address address) throws ClientException {
        if (email.equals(""))
            throw new ClientException("Email field is not set");
        if (name.equals(""))
            throw new ClientException("Last name field is not set");
        if (address == null)
            throw new ClientException("Address is not set");

        this.email = email;
        this.name = name;
        this.address = address;
    }

}
