
#include <boost/lexical_cast.hpp>
#include "Managers/ClientManager.h"
#include "Repositories/ClientRepository.h"
#include "Predicates/ClientUnaryPredicate.h"
#include "algorithm"
#include "General/Address.h"
#include "General/Client.h"
#include "Exception/ClientException.h"
ClientManager::ClientManager(std::shared_ptr<ClientRepository> clientRepository) : clientRepository(
        clientRepository)
{
    if(this->clientRepository== nullptr)
        this->clientRepository=std::shared_ptr<ClientRepository>(new ClientRepository());
}

ClientManager::~ClientManager()
{

}

void ClientManager::addClient(std::shared_ptr<Client> client)
{
    std::vector<Client_shared_ptr> temp = this->clientRepository->getAll();
    for (auto f:temp)
    {
        if(f->toString()==client->toString())throw ClientExeption("there is the same element in repo")      ;
    }
    this->clientRepository->add(client);


}


std::shared_ptr<Client> ClientManager::findClient(std::shared_ptr<ClientUnaryPredicate> unaryPredicate)
{

    std::vector<Client_shared_ptr> temp_repo;
    temp_repo = this->clientRepository->getAll();


    std::shared_ptr<findClientPersonalID> F_ID = std::dynamic_pointer_cast<findClientPersonalID>(unaryPredicate);
    if (F_ID != nullptr)
    {
        std::vector<std::shared_ptr<Client>>::iterator it = std::find_if(temp_repo.begin(), temp_repo.end(), *F_ID);
        if (it != temp_repo.end())
        {
            return it[0];
        }
        return nullptr;
    }

    std::shared_ptr<findClientInfo> F_Info = std::dynamic_pointer_cast<findClientInfo>(unaryPredicate);
    if (F_Info != nullptr)
    {
        std::vector<std::shared_ptr<Client>>::iterator it = std::find_if(temp_repo.begin(), temp_repo.end(), *F_Info);
        if (it != temp_repo.end())
        {
            return it[0];
        }
        return nullptr;
    }

    std::shared_ptr<findClientFirstName> F_first = std::dynamic_pointer_cast<findClientFirstName>(unaryPredicate);
    if (F_first != nullptr)
    {
        std::vector<std::shared_ptr<Client>>::iterator it = std::find_if(temp_repo.begin(), temp_repo.end(), *F_first);
        if (it != temp_repo.end())
        {
            return it[0];
        }
        return nullptr;
    }

    std::shared_ptr<findClientLastName> F_last = std::dynamic_pointer_cast<findClientLastName>(unaryPredicate);
    if (F_last != nullptr)
    {
        std::vector<std::shared_ptr<Client>>::iterator it = std::find_if(temp_repo.begin(), temp_repo.end(), *F_last);
        if (it != temp_repo.end())
        {
            return it[0];
        }
        return nullptr;
    }


}

void ClientManager::createClient(std::string firstName, std::string lastName, std::string personalId,
                                 std::shared_ptr<Address> address)
{
    this->addClient(std::shared_ptr<Client>(new Client(firstName, lastName, personalId, address)));
}

void ClientManager::removeClient(int position)
{
    std::vector<Client_shared_ptr> temp = this->clientRepository->getAll();
    std::shared_ptr<Client> tempClient;
    if(position-1>=temp.size())throw " there is no so many clients";
    tempClient = temp[position-1];
    this->clientRepository->remove(tempClient);
}

void ClientManager::removeClient(std::string id)
{
    std::shared_ptr<ClientUnaryPredicate> temp = std::shared_ptr<ClientUnaryPredicate>(new findClientPersonalID(id));
    this->clientRepository->remove(this->findClient(temp));
}

std::shared_ptr<Client> ClientManager::getClient(int position)
{
    std::vector<Client_shared_ptr> temp = this->clientRepository->getAll();
    std::shared_ptr<Client> tempClient;
    if(position-1>=temp.size())throw " there is no so many clients";
    tempClient = temp[position-1];
    return tempClient;
}

