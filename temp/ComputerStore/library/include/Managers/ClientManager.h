#ifndef COMPUTER_STORE_CLIENTMANAGER_H
#define COMPUTER_STORE_CLIENTMANAGER_H

#include "memory"
#include "string"
class Client;
class ClientRepository;
class ClientUnaryPredicate;
class Address;

class ClientManager {
    std::shared_ptr<ClientRepository> clientRepository;
private:
public:
    ClientManager(std::shared_ptr<ClientRepository> clientRepository= nullptr);

    void addClient(std::shared_ptr<Client> client);

    virtual ~ClientManager();


    std::shared_ptr<Client> findClient(std::shared_ptr<ClientUnaryPredicate> unaryPredicate);

    void createClient(std::string firstName, std::string lastName, std::string personalId,
                      std::shared_ptr<Address> address);

    void removeClient(int position);
    void removeClient(std::string dataStr);
    std::shared_ptr<Client> getClient(int position);

};


#endif //COMPUTER_STORE_CLIENTMANAGER_H
