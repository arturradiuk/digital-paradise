package model.entities;

import controller.exceptions.ClientException;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class Person {
    private UUID uuid;

    private String email;

    private String name;

    private Address address;


    public Person(String email, String name, Address address) throws ClientException {
        if (email.equals(""))
            throw new ClientException("Email field is not set");
        if (name.equals(""))
            throw new ClientException("Last name field is not set");
        if (address == null)
            throw new ClientException("Address is not set");

        this.uuid = UUID.randomUUID();
        this.email = email;
        this.name = name;
        this.address = address;
    }

}
