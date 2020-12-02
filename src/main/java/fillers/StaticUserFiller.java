package fillers;

import controller.exceptions.AddressException;
import controller.exceptions.ClientException;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.Address;
import model.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaticUserFiller implements DataFiller { // todo rename to people filler
    @Override
    public List<User> Fill() {
        List<User> people = new ArrayList<>();
        Address address = null;
        try {
            address = new Address("Street", "number");
        } catch (AddressException e) {
            e.printStackTrace();
        }
        try {
            User temp = new Client("Tola@gmail.com", "Tola", address);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("1234567890_person").getBytes()));
            people.add(temp);

           User temp2 = new Client("Lolek@gmail.com", "Lolek", address);
            temp2.setUuid(UUID.nameUUIDFromBytes(new String("2234567890_person").getBytes()));
            people.add(temp2);

            temp = new Client("Bolek@gmail.com", "Bolek", address);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("3234567890_person").getBytes()));
            people.add(temp);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("434567890_person").getBytes()));
            temp = new Employee("John@gmail.com", "John", address);
            people.add(temp);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("534567890_person").getBytes()));
            temp = new Administrator("Bob@gmail.com", "Bob", address);
            people.add(temp);


        } catch (ClientException e) {
            e.printStackTrace();
        }
        return people;
    }
}
