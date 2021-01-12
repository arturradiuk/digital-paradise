package temp;

import controller.exceptions.user.ClientException;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws ClientException {
        String aString="123456789";
        String result = UUID.nameUUIDFromBytes(aString.getBytes()).toString();
        System.out.println(result);
        System.out.println(UUID.nameUUIDFromBytes(aString.getBytes()).toString());
        System.out.println(UUID.fromString("26135e78-c523-46b3-b4ed-a71ff26ef92d"));

    }
}
