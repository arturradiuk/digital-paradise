package model.repositories;

import controller.exceptions.RepositoryException;
import lombok.NoArgsConstructor;
import model.entities.Client;

import java.util.List;

@NoArgsConstructor
public class ClientRepository implements Repository<Client> {

    private List<Client> clients;

    public ClientRepository(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void add(Client element) throws RepositoryException {
        for (Client client:clients) {
            if(client.equals(element))
                throw new RepositoryException("This client already exists");
        }
        this.clients.add(element);
    }

    @Override
    public void remove(Client element) throws RepositoryException {
        for (Client client:clients) {
            if(client.equals(element)) {
                this.clients.remove(element);
            }
        }
        throw new RepositoryException("This client doesn't exist");
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public String toString() {
        return "ClientRepository{" +
                "clients=" + clients +
                '}';
    }
}
