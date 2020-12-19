package model.clients;

import controller.exceptions.AdminException;
import controller.exceptions.ClientException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.entities.Address;
import model.entities.User;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Client extends User {

    private String phoneNumber;

    public Client(String email, String name, Address address, String phoneNumber) throws ClientException {
        super(email, name, address);
        if (phoneNumber == null)
            throw new ClientException("Phone number is not set");
        this.phoneNumber = phoneNumber;
    }
}
