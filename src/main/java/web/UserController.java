package web;

import controller.exceptions.RepositoryException;
import controller.managers.UserManager;
import lombok.Data;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@SessionScoped
public class UserController implements Serializable {
    @Inject
    private UserManager userManager;

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
        this.userManager.addClient(this.newAdministrator);
        this.newAdministrator = new Administrator();
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String processNewEmployee() {
        if (this.newEmployee == null || this.newEmployee.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwirdzenia NewEmployee bez name danych.");
        }
        this.userManager.addClient(this.newEmployee);
        this.newEmployee = new Employee();
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String processNewClient() {
        if (this.newClient == null || this.newClient.getName().isEmpty()) {
            throw new IllegalArgumentException("Proba zatwirdzenia newClient bez name danych.");
        }
        this.userManager.addClient(this.newClient);
        this.newClient = new Client();
        this.initCurrentUsers();
        return "AllUsers";
    }
    public void changeClientActivity(User user)
    {
        this.userManager.getClientByUUID(user.getUuid()).changeActivity();
    }


    @PostConstruct
    public void initCurrentUsers() {
        this.currentAdministrators = this.userManager.getAllAdministrators();
        this.currentEmployees = this.userManager.getAllEmployees();
        this.currentClients = this.userManager.getAllClients();
    }

    public String removeUser(User user) {
        this.userManager.removeUser(user);
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String refresh() {
        System.out.println("in user refresh");
        this.initCurrentUsers();
//        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
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

    public String updateAdministrator(){
        this.userManager.updateUser(this.currentAdministrator.getUuid(),this.currentAdministrator);
        this.initCurrentUsers();
        return "AllUsers";
    }

    public String updateEmployee(){
        this.userManager.updateUser(this.currentEmployee.getUuid(),this.currentEmployee);
        this.initCurrentUsers();
        return "AllUsers";
    }
    public String updateClient(){
        this.userManager.updateUser(this.currentClient.getUuid(),this.currentClient);
        this.initCurrentUsers();
        return "AllUsers";
    }

}
