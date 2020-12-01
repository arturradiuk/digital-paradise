package temp;

import controller.exceptions.AddressException;
import controller.exceptions.ClientException;
import model.clients.Administrator;
import model.clients.Employee;
import model.entities.Address;
import model.entities.Person;

public class Main {
    public static void main(String[] args) throws ClientException {
        Address address = null;
        try {
            address = new Address("Street", "number");
        } catch (AddressException e) {
            e.printStackTrace();
        }
        Person employee = new Employee("John@gmail.com", "John", address);
        Person administrator = new Administrator("Bob@gmail.com", "Bob", address);
        if (administrator instanceof Employee){
            System.out.println("Employee");
        }
    }
}
