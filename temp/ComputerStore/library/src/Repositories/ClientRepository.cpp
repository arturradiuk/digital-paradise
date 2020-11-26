#include "Repositories/ClientRepository.h"


void ClientRepository::add(Client_shared_ptr client) {
    clients.push_back(client);
}

void ClientRepository::remove(Client_shared_ptr client) {
    for (unsigned int i = 0; i < clients.size(); i++)
        if (clients[i] == client)
            clients.erase(clients.begin()+i);
}

std::vector<Client_shared_ptr> ClientRepository::getAll() {
    return clients;
}

std::string ClientRepository::toString() {
    std::string res;

    for (auto &&i : clients)
        res += i->toString() + "\n";

    return res;
}
