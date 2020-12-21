package web;

import controller.exceptions.repository.RepositoryException;
import controller.managers.UserManager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@Named
@SessionScoped
public class UserController implements Serializable {
    @Inject
    private UserManager userManager;

    @Setter
    @Getter
    private String uuid = new String();

    private User newAdministrator = new Administrator();
    private User newEmployee = new Employee();
    private User newClient = new Client();

    private User currentAdministrator = new Administrator();
    private User currentEmployee = new Employee();
    private User currentClient = new Client();

    private List<User> currentAdministrators;
    private List<User> currentEmployees;
    private List<User> currentClients;

    public String processNewAdministrator() {
        if (this.newAdministrator == null || this.newAdministrator.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwirdzenia NewAdministrator bez name danych.");
        }
        try {
            this.userManager.addClient(this.newAdministrator);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page

        }
        this.newAdministrator = new Administrator();
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String processNewEmployee() {
        if (this.newEmployee == null || this.newEmployee.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwirdzenia NewEmployee bez name danych.");
        }
        try {
            this.userManager.addClient(this.newEmployee);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page

        }
        this.newEmployee = new Employee();
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String processNewClient() {
        if (this.newClient == null || this.newClient.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwirdzenia newClient bez name danych.");
        }
        try {
            this.userManager.addClient(this.newClient);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page

        }
        this.newClient = new Client();
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

    public String removeUser(User user) throws RepositoryException {
        this.userManager.removeUser(user);
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String refresh() {
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String seeAdministrator(Administrator administrator) {
        this.currentAdministrator = administrator;
        return "UpdateAdministrator";
    }

    public String seeEmployee(Employee employee) {
        this.currentEmployee = employee;
        return "UpdateEmployee";
    }

    public String seeClient(Client client) {
        this.currentClient = client;
        return "UpdateClient";
    }

    public String updateAdministrator() {
        try {
            this.userManager.updateUser(this.currentAdministrator.getUuid(), this.currentAdministrator);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page
        }
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String updateEmployee() {
        try {
            this.userManager.updateUser(this.currentEmployee.getUuid(), this.currentEmployee);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page
        }
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String updateClient() {
        try {
            this.userManager.updateUser(this.currentClient.getUuid(), this.currentClient);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page
        }
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String findUserById() {
        this.initCurrentUsersById();
        return "FindById";
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
