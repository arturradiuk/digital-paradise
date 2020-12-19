package model.clients;

import controller.exceptions.AdminException;
import controller.exceptions.ClientException;
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
public class Administrator extends User {
    public Boolean getHeadAdmin() {
        return isHeadAdmin;
    }

    public void setHeadAdmin(Boolean headAdmin) {
        isHeadAdmin = headAdmin;
    }

    private Boolean isHeadAdmin;

    public Administrator(String email, String name, Address address, Boolean isHeadAdmin) throws ClientException, AdminException {
        super(email, name, address);
        if (isHeadAdmin == null)
            throw new AdminException("HeadAdmin is not set");
        this.isHeadAdmin = isHeadAdmin;
    }
}
