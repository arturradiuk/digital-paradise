#ifndef COMPUTER_STORE_CLIENTREPOSITORY_H
#define COMPUTER_STORE_CLIENTREPOSITORY_H

#include "General/Repository.h"
#include "General/Client.h"

class ClientRepository : public Repository<Client> {
    std::vector<std::shared_ptr<Client>> clients;
public:
    void add(std::shared_ptr<Client> client) override;
    void remove(std::shared_ptr<Client> client) override;
    std::vector<std::shared_ptr<Client>> getAll() override;

    std::string toString() override;
};


#endif //COMPUTER_STORE_CLIENTREPOSITORY_H
