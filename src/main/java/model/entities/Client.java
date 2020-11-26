package model.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Client {
    @Getter
    private UUID uuid;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private Address address;

    public Client(UUID uuid, String firstName, String lastName, Address address) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
