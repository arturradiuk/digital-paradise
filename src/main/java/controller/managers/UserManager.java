package controller.managers;

import controller.exceptions.manager.GoodManagerException;
import controller.exceptions.manager.UserManagerException;
import controller.exceptions.repository.RepositoryException;
import lombok.NoArgsConstructor;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.Order;
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
public class UserManager implements IManager<User, UUID> {

    @Inject
    private Repository<User, UUID> userRepository;

    @Override
    public void add(User user) throws RepositoryException {
        this.userRepository.add(user);
    }

    @Override
    public void remove(User user) throws RepositoryException {
        this.userRepository.remove(user);
    }

    public void remove(OrderManager orderManager, User user) throws UserManagerException, RepositoryException {
        for (Order o : orderManager.getAll()) {
            if (o.getClient().getUuid().equals(user.getUuid()))
                throw new UserManagerException(UserManagerException.CANNOT_DELETE_USER);
        }
        this.userRepository.remove(user);

    }

    @Override
    public void update(UUID uuid, User user) throws RepositoryException {
        this.userRepository.update(uuid, user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.getAll();
    }


    public User getUserByUUID(UUID uuid) throws RepositoryException {
       return this.userRepository.getResourceByUUID(uuid);
    }


    public List<User> getAllClients() {
        return this.getAll().stream().filter(person -> person instanceof Client).collect(Collectors.toList());
    }

    public List<User> getAllEmployees() {
        return this.getAll().stream().filter(person -> person instanceof Employee).collect(Collectors.toList());
    }

    public List<User> getAllAdministrators() {
        return this.getAll().stream().filter(person -> person instanceof Administrator).collect(Collectors.toList());
    }

}
