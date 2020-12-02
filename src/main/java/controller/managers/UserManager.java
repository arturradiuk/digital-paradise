package controller.managers;

import controller.exceptions.RepositoryException;
import lombok.NoArgsConstructor;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.User;
import model.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
@NoArgsConstructor
public class UserManager {
    @Inject
    private Repository<User> clientRepository;// = new PersonRepository();

    public void addClient(User user) {
        try {
            clientRepository.add(user);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        } finally {

        }
    }

    public void removeClient(User user) {
        try {
            this.clientRepository.remove(user);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }

    public User getClientByLogin(String email) {
        User user = this.clientRepository.getAll().stream()
                .filter(c -> c.getEmail().equals(email)).findFirst().orElse(null);
        return user;
    }

    public User getClientByUUID(UUID uuid) {
        User user = this.clientRepository.getAll().stream()
                .filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        return user;
    }

    public List<User> getAllPerson() {
        return this.clientRepository.getAll();
    }

    public List<User> getAllClients() {
        List<User> people = this.getAllPerson().stream().filter(person -> person instanceof Client).collect(Collectors.toList());
        return people;
    }
    public List<User> getAllEmployees() { // todo move to the repository
        List<User> people = this.getAllPerson().stream().filter(person -> person instanceof Employee).collect(Collectors.toList());
        return people;
    }
    public List<User> getAllAdministrators() {
        List<User> people = this.getAllPerson().stream().filter(person -> person instanceof Administrator).collect(Collectors.toList());
        return people;
    }


}
