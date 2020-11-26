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

    public Client(String firstName, String lastName, Address address) {
        this.uuid = UUID.randomUUID();
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

    @Override
    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (ob == null)
            return false;
        if (getClass() != ob.getClass())
            return false;
        Client other = (Client) ob;
        if(other.getUuid() == this.uuid){
            return true;
        }
        return false;

    }
}
