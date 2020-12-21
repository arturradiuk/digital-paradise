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
    private UUID managerUuid = UUID.randomUUID();

    @Inject
    private Repository<User, UUID> userRepository;// = new PersonRepository();

    public void addClient(User user) {
        try {
            userRepository.add(user);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        } finally {

        }
    }


    public void removeUser(User user) {
        try {
            this.userRepository.remove(user);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }


    public User getUserByUUID(UUID uuid) {
        User user = this.userRepository.getAll().stream()
                .filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        return user;
    }

    public List<User> getAllPerson() {
        return this.userRepository.getAll();
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

    public void updateUser(UUID uuid, User user) {
        try {
            this.userRepository.update(uuid, user);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

}
