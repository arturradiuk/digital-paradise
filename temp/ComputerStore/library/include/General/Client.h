#ifndef COMPUTER_STORE_CLIENT_H
#define COMPUTER_STORE_CLIENT_H

#include "memory"
#include "string"
#include "vector"
class Order;
class Address;
class ClientType;

class Client {
    std::string firstName;
    std::string lastName;
    std::string personalId;

    std::shared_ptr<Address> address;

    std::shared_ptr<ClientType> clientType;
    std::vector<std::weak_ptr<Order>> orders;

public:


    Client(std::string firstName, std::string lastName, std::string personalId,
           std::shared_ptr<Address> address);
    virtual ~Client();
    void setOrder(std::weak_ptr<Order> order);
    void changeClientType(int clientType);
    std::string toString();
    std::string getFirstName();
    void setFirstName(std::string firstName);
    std::string getLastName() ;
    void setLastName(std::string lastName);
    std::string getPersonalId();
    void setPersonalId(std::string personalId);
    std::shared_ptr<Address> getAddress();
    std::string getAddressData();
    std::string getClientTypeData();
    std::shared_ptr<ClientType> getClientType();

};

typedef std::shared_ptr<Client> Client_shared_ptr;
typedef std::weak_ptr<Client> Client_weak_ptr;
#endif //COMPUTER_STORE_CLIENT_H
