package model.repositories;

import model.entities.Client;

import java.util.List;

public class ClientRepository implements Repository<Client> {

    private List clients;



    public ClientRepository(List clients) {
        this.clients = clients;
    }

    @Override
    public void add(Client element) {
        this.clients.add(element);
    }

    @Override
    public void remove(Client element) {

    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public String toString() {
        return "ClientRepository{" +
                "clients=" + clients +
                '}';
    }
}
