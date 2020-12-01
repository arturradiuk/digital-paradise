package controller.managers;

import controller.exceptions.RepositoryException;
import lombok.NoArgsConstructor;
import model.clients.Administrator;
import model.clients.Client;
import model.clients.Employee;
import model.entities.Person;
import model.repositories.PersonRepository;
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
public class PersonManager {
    @Inject
    private Repository<Person> clientRepository;// = new PersonRepository();

    public void addClient(Person person) {
        try {
            clientRepository.add(person);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        } finally {

        }
    }

    public void removeClient(Person person) {
        try {
            this.clientRepository.remove(person);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }

    public Person getClientByLogin(String email) {
        Person person = this.clientRepository.getAll().stream()
                .filter(c -> c.getEmail().equals(email)).findFirst().orElse(null);
        return person;
    }

    public Person getClientByUUID(UUID uuid) {
        Person person = this.clientRepository.getAll().stream()
                .filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        return person;
    }

    public List<Person> getAllPerson() {
        return this.clientRepository.getAll();
    }

    public List<Person> getAllClients() {
        List<Person> people = this.getAllPerson().stream().filter(person -> person instanceof Client).collect(Collectors.toList());
        return people;
    }
    public List<Person> getAllEmployees() {
        List<Person> people = this.getAllPerson().stream().filter(person -> person instanceof Employee).collect(Collectors.toList());
        return people;
    }
    public List<Person> getAllAdministrators() {
        List<Person> people = this.getAllPerson().stream().filter(person -> person instanceof Administrator).collect(Collectors.toList());
        return people;
    }


}
