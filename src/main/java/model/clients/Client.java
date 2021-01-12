package model.clients;

import java.util.UUID;
import controller.exceptions.user.ClientException;
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
public class Client extends User {

    private String phoneNumber;
    private Boolean active = true;

    public Client(String email, String name, Address address, String phoneNumber) throws UserException {
        super(email, name, address);

        if (phoneNumber == null)
            throw new ClientException(ClientException.NULL_FIELD);
        if (phoneNumber.equals(""))
            throw new ClientException(ClientException.EMPTY_FIELD);

        this.phoneNumber = phoneNumber;
        this.active = true;
    }
    
    public Client(UUID uuid, String email, String name, Address address, String phoneNumber) throws UserException {
        super(uuid, email, name, address);

        if (phoneNumber == null)
            throw new ClientException(ClientException.NULL_FIELD);
        if (phoneNumber.equals(""))
            throw new ClientException(ClientException.EMPTY_FIELD);

        this.phoneNumber = phoneNumber;
        this.active = true;
    }

    @Override
    public void changeActivity() {
        this.active = !this.active;
    }
}
