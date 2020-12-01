package temp;

import controller.exceptions.AddressException;
import controller.exceptions.ClientException;
import model.clients.Administrator;
import model.clients.Employee;
import model.entities.Address;
import model.entities.Person;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws ClientException {
        String aString="123456789";
        String result = UUID.nameUUIDFromBytes(aString.getBytes()).toString();
        System.out.println(result);
        System.out.println(UUID.nameUUIDFromBytes(aString.getBytes()).toString());

    }
}
