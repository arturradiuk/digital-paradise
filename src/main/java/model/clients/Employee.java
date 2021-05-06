package model.clients;

import java.util.UUID;
import controller.exceptions.user.EmployeeException;
import controller.exceptions.user.UserException;
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
public class Employee extends User {

    private Float earnings;

    public Employee(String email, String name, Address address, Float earnings) throws  UserException {
        super(email, name, address);
        if (earnings == null)
            throw new EmployeeException(EmployeeException.NULL_FIELD);

        this.earnings = earnings;
    }
    
    public Employee(UUID uuid, String email, String name, Address address, Float earnings) throws  UserException {
        super(uuid, email, name, address);
        if (earnings == null)
            throw new EmployeeException(EmployeeException.NULL_FIELD);

        this.earnings = earnings;
    }
}
