package model.clients;

import controller.exceptions.user.AdminException;
import controller.exceptions.user.UserException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.entities.Address;
import model.entities.User;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Administrator extends User {
    
    @Getter @Setter
    private Boolean isHeadAdmin;

    public Administrator(String email, String name, Address address, Boolean isHeadAdmin) throws UserException {
        super(email, name, address);

        if (isHeadAdmin == null)
            throw new AdminException(AdminException.NULL_FIELD);

        if (isHeadAdmin.equals(""))
            throw new AdminException(AdminException.EMPTY_FIELD);

        this.isHeadAdmin = isHeadAdmin;
    }
}
