package fillers;

import controller.exceptions.*;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.Address;
import model.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class StaticUserFiller implements DataFiller { // todo rename to people filler
    @Override
    public List<User> Fill() {
        List<User> people = new CopyOnWriteArrayList<>();
        Address address = null;
        try {
            address = new Address("Street", "number");
        } catch (AddressException e) {
            e.printStackTrace();
        }
        try {
            User temp = new Client("Tola@gmail.com", "Tola", address,"672817289");
            temp.setUuid(UUID.nameUUIDFromBytes(new String("1234567890_person").getBytes()));
            people.add(temp);

           User temp2 = new Client("Lolek@gmail.com", "Lolek", address,"672817289");
            temp2.setUuid(UUID.nameUUIDFromBytes(new String("2234567890_person").getBytes()));
            people.add(temp2);

            temp = new Client("Bolek@gmail.com", "Bolek", address,"672817289");
            temp.setUuid(UUID.nameUUIDFromBytes(new String("3234567890_person").getBytes()));
            people.add(temp);
//

            temp.setUuid(UUID.nameUUIDFromBytes(new String("434567890_person").getBytes()));
            temp = new Employee("TolaEmployee@gmail.com", "TolaEmployee", address, (float) 2800);
            people.add(temp);

            temp.setUuid(UUID.nameUUIDFromBytes(new String("534567890_person").getBytes()));
            temp = new Employee("LolekEmployee@gmail.com", "LolekEmployee", address, (float) 3000);
            people.add(temp);

            temp.setUuid(UUID.nameUUIDFromBytes(new String("634567890_person").getBytes()));
            temp = new Employee("BolekEmployee@gmail.com", "BolekEmployee", address, (float) 5000);
            people.add(temp);

//


            temp.setUuid(UUID.nameUUIDFromBytes(new String("734567890_person").getBytes()));
            temp = new Administrator("TolaAdministrator@gmail.com", "TolaAdministrator", address,true);
            people.add(temp);

            temp.setUuid(UUID.nameUUIDFromBytes(new String("834567890_person").getBytes()));
            temp = new Administrator("LolekAdministrator@gmail.com", "LolekAdministrator", address, false);
            people.add(temp);

            temp.setUuid(UUID.nameUUIDFromBytes(new String("934567890_person").getBytes()));
            temp = new Administrator("BolekAdministrator@gmail.com", "BolekAdministrator", address, false);
            people.add(temp);


        } catch (ClientException | AdminException | EmployeeException | UserException e) {
            e.printStackTrace();
        }
        return people;
    }
}
