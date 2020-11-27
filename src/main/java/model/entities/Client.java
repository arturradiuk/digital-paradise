package model.entities;

import controller.exceptions.AddressException;
import controller.exceptions.ClientException;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Client {
    @Getter
    private UUID uuid;

    @Getter @Setter
    private String email;


    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private Address address;

    public Client(String email, String lastName, Address address) throws ClientException {
        if(email.equals(""))
            throw new ClientException("Email field is not set");
        if(lastName.equals(""))
            throw new ClientException("Last name field is not set");
        if(address == null)
            throw new ClientException("Address is not set");

        this.uuid = UUID.randomUUID();
        this.email = email;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid +
                ", firstName='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (ob == null)
            return false;
        if (getClass() != ob.getClass())
            return false;
        Client other = (Client) ob;

        return other.getEmail().equals(this.email) && other.getUuid() == uuid;

    }
}
