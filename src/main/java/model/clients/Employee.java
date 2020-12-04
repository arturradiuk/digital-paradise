package model.clients;

import controller.exceptions.ClientException;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.entities.Address;
import model.entities.User;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Employee extends User {
    public Employee(String email, String name, Address address) throws ClientException {
        super(email, name, address);
    }
}
