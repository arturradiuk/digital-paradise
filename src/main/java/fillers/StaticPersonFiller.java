package fillers;

import controller.exceptions.AddressException;
import controller.exceptions.ClientException;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.Address;
import model.entities.Person;

import java.util.ArrayList;
import java.util.List;

public class StaticPersonFiller implements DataFiller { // todo rename to people filler
    @Override
    public List<Person> Fill() {
        List<Person> people = new ArrayList<>();
        Address address = null;
        try {
            address = new Address("Street", "number");
        } catch (AddressException e) {
            e.printStackTrace();
        }
        try {
            people.add(new Client("Tola@gmail.com", "Tola", address));
            people.add(new Client("Lolek@gmail.com", "Lolek", address));
            people.add(new Client("Bolek@gmail.com", "Bolek", address));
            people.add(new Employee("John@gmail.com", "John", address));
            people.add(new Administrator("Bob@gmail.com", "Bob", address));


        } catch (ClientException e) {
            e.printStackTrace();
        }
        return people;
    }
}
