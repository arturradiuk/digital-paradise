package web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import controller.exceptions.ManagerException;
import controller.exceptions.repository.RepositoryException;
import controller.managers.OrderManager;
import controller.managers.UserManager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.User;

@Data
@Named
@SessionScoped
public class UserController implements Serializable {
    @Inject
    private UserManager userManager;
    @Inject
    private OrderManager orderManager;

    @Setter
    @Getter
    private String uuid = "";

    private User newUser;

    private User currentUser;

    private List<User> currentAdministrators;
    private List<User> currentEmployees;
    private List<User> currentClients;

    public String processNewAdministrator() {
        if (this.newUser == null) {
            this.addAdministrator();
        } else if (this.newUser.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwierdzenia NewAdministrator bez name danych.");
        }
        try {
            this.userManager.add(this.newUser);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return "AddUserFailure";
        }
        //this.newAdministrator = new Administrator();
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String processNewEmployee() {
        if (this.newUser == null) {
            this.addEmployee();
        } else if (this.newUser.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwirdzenia NewEmployee bez name danych.");
        }
        try {
            this.userManager.add(this.newUser);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return "AddUserFailure";

        }
        //this.newUser = new Employee();
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String processNewClient() {
        if (this.newUser == null) {
            this.addClient();
        } else if (this.newUser.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwierdzenia newClient bez name danych.");
        }
        try {
            this.userManager.add(this.newUser);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return "AddUserFailure";
        }
        //        this.newUser = new Client();
        this.initCurrentUsers();
        return "AllUsers";
    }

    public void changeClientActivity(User user) {
        this.userManager.getUserByUUID(user.getUuid()).changeActivity();
    }


    @PostConstruct
    public void initCurrentUsers() {
        this.currentAdministrators = this.userManager.getAllAdministrators();
        this.currentEmployees = this.userManager.getAllEmployees();
        this.currentClients = this.userManager.getAllClients();
    }

    public String removeUser(User user) {
        try {
            //            this.userManager.remove(user);
            this.userManager.remove(orderManager, user);
        } catch (RepositoryException | ManagerException e) {
            e.printStackTrace();
            return "RemoveUserFailure";
        }
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String refresh() {
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String seeAdministrator(Administrator administrator) {
        this.currentUser = administrator;
//        return "UpdateAdministrator";
        return "UpdateUser";
    }

    public String seeEmployee(Employee employee) {
        this.currentUser = employee;
//        return "UpdateEmployee";
        return "UpdateUser";
    }

    public String seeClient(Client client) {
        this.currentUser = client;
//        return "UpdateClient";
        return "UpdateUser";
    }
    
    

    public String addClient() {
        this.newUser = new Client();
        return "AddUser";
    }

    public String addEmployee() {
        this.newUser = new Employee();
        return "AddUser";
    }

    public String addAdministrator() {
        this.newUser = new Administrator();
        return "AddUser";
    }

    public boolean isNewUserClient() {
        if (this.newUser == null) {
            goToAllUsers();
            return false;
        }
        return this.newUser.getClass().equals(Client.class);
    }

    public boolean isNewUserEmployee() {
        if (this.newUser == null) {
            goToAllUsers();
            return false;
        }
        return this.newUser.getClass().equals(Employee.class);
    }

    public boolean isNewUserAdministrator() {
        if (this.newUser == null) {
            goToAllUsers();
            return false;
        }
        return this.newUser.getClass().equals(Administrator.class);
    }

    public boolean isCurrentUserClient() {
        if (this.currentUser == null) {
            goToAllUsers();
            return false;
        }
        return this.currentUser.getClass().equals(Client.class);
    }

    public boolean isCurrentUserEmployee() {
        if (this.currentUser == null) {
            goToAllUsers();
            return false;
        }
        return this.currentUser.getClass().equals(Employee.class);
    }

    public boolean isCurrentUserAdministrator() {
        if (this.currentUser == null) {
            goToAllUsers();
            return false;
        }
        return this.currentUser.getClass().equals(Administrator.class);
    }

    public String updateUser() {
        try {
            this.userManager.update(this.currentUser.getUuid(), this.currentUser);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return "UserUpdateFailure";
        }
        this.initCurrentUsers();
        return "AllUsers";
    }

//    public String updateAdministrator() {
//        try {
//            this.userManager.update(this.currentAdministrator.getUuid(), this.currentAdministrator);
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//            return "UserUpdateFailure";
//        }
//        this.initCurrentUsers();
//        return "AllUsers";
//    }
//
//    public String updateEmployee() {
//        try {
//            this.userManager.update(this.currentEmployee.getUuid(), this.currentEmployee);
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//            return "UserUpdateFailure";
//        }
//        this.initCurrentUsers();
//        return "AllUsers";
//    }
//
//    public String updateClient() {
//        try {
//            this.userManager.update(this.currentClient.getUuid(), this.currentClient);
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//            return "UserUpdateFailure";
//        }
//        this.initCurrentUsers();
//        return "AllUsers";
//    }

    public String findUserById() {
        this.initCurrentUsersById();
        return "FindById";
    }
    
    public String goToAllUsers() {
        return "AllUsers";
    }

    private void initCurrentUsersById() {
        User u = this.userManager.getUserByUUID(UUID.fromString(this.uuid));

        if (u instanceof Administrator) {
            this.currentAdministrators = new CopyOnWriteArrayList<>();
            this.currentAdministrators.add(u);
        }
        if (u instanceof Client) {
            this.currentClients = new CopyOnWriteArrayList<>();
            this.currentClients.add(u);
        }
        if (u instanceof Employee) {
            this.currentEmployees = new CopyOnWriteArrayList<>();
            this.currentEmployees.add(u);
        }
    }
}
