package model.clients;

import controller.exceptions.AdminException;
import controller.exceptions.ClientException;
import controller.exceptions.EmployeeException;
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


    public Employee(String email, String name, Address address, Float earnings) throws ClientException, EmployeeException {
        super(email, name, address);
        if (earnings == null)
            throw new EmployeeException("HeadAdmin is not set");
        this.earnings = earnings;
    }
}
