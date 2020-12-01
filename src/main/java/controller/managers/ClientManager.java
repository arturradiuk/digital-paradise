package controller.managers;

import controller.exceptions.RepositoryException;
import lombok.NoArgsConstructor;
import model.entities.Client;
import model.repositories.ClientRepository;
import model.repositories.Repository;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class ClientManager {
    private Repository<Client> clientRepository = new ClientRepository();

    public void addClient(Client client) {
        try {
            clientRepository.add(client);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        } finally {

        }
    }

    public void removeClient(Client client) {
        try {
            this.clientRepository.remove(client);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }

    public Client getClientByLogin(String email) {
        Client client = this.clientRepository.getAll().stream()
                .filter(c -> c.getEmail().equals(email)).findFirst().orElse(null);
        return new Client();
    }

    public Client getClientByUUID(UUID uuid) {
        Client client = this.clientRepository.getAll().stream()
                .filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        return client;
    }

    public List<Client> getAllClients(){
        return this.clientRepository.getAll();
    }


}
