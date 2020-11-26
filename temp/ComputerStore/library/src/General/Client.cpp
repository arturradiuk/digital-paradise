#include "General/Client.h"
#include "General/Address.h"
#include "General/ClientType.h"
#include "ClientType/ClientTypeBusiness.h"
#include "ClientType/ClientTypeDefault.h"
#include "ClientType/ClientTypeGold.h"
#include "ClientType/ClientTypePremium.h"
#include "General/Good.h"
#include "General/Order.h"
#include "General/Delivery.h"
#include "Exception/ClientException.h"

#include "sstream"

Client::Client(std::string firstName, std::string lastName, std::string personalId,
               std::shared_ptr<Address> address) : firstName(firstName), lastName(lastName),
                                                   personalId(personalId), address(address)
{
    this->changeClientType(1);
    if(firstName=="") throw ClientExeption("firstName field is empty!");
    if(lastName=="") throw ClientExeption("lastName field is empty!");
    if(personalId=="") throw ClientExeption("personalId field is empty!");
    if(address== nullptr) throw ClientExeption("personalId field is empty!");
}

Client::~Client() {

}


void Client::changeClientType(int clientType) {
    switch (clientType)
    {
        case 1:
            this->clientType=std::shared_ptr<ClientTypeDefault>(new ClientTypeDefault);
            return;

        case 2:
            this->clientType=std::shared_ptr<ClientTypeBusiness>(new ClientTypeBusiness);
            return;


        case 3:
            this->clientType=std::shared_ptr<ClientTypeGold>(new ClientTypeGold);
            return;


        case 4:
            this->clientType=std::shared_ptr<ClientTypePremium>(new ClientTypePremium);
            return;

        default:
            this->clientType=std::shared_ptr<ClientTypeDefault>(new ClientTypeDefault);
            return;

    }
}

std::string Client::toString() {
    std::string temp;
    std::stringstream os;

    for (int i = 0; i < this->orders.size(); ++i) {
        os<<"\n  "+std::to_string(i+1)+")"+" Order ID = "+ boost::uuids::to_string(this->orders[i].lock()->getId())<<
                "\n     "+orders[i].lock()->getGood()->toString()<<
                "\n     Delivery price = " + std::to_string(this->orders[i].lock()->getDelivery().getDeliveryPrice())<<
                "\n     Total price = "+std::to_string(this->orders[i].lock()->getFullPrice());
    }
    return "->Client:\n  - First Name = " + this->firstName
           +", Last Name = "+this->lastName+", Personal Id = "+personalId+
           "\n  - " + this->address->toString()+
           "\n  - " + this->getClientTypeData()+
           "\n Order of this client: "+ os.str();
}


std::string Client::getFirstName(){
    return firstName;
}

std::string Client::getLastName() {
    return lastName;
}

void Client::setFirstName(std::string firstName) {
    Client::firstName = firstName;
}

void Client::setLastName(std::string lastName) {
    Client::lastName = lastName;
}

std::string Client::getPersonalId() {
    return personalId;
}

void Client::setPersonalId(std::string personalId) {
    Client::personalId = personalId;
}

void Client::setOrder(std::weak_ptr<Order> order) {
    this->orders.push_back(order);
}

std::string Client::getAddressData() {
    return this->address->toString();
}

std::string Client::getClientTypeData() {
    return "Client Type::"+ this->clientType->toString();
}

std::shared_ptr<ClientType> Client::getClientType(){
    return clientType;
}

std::shared_ptr<Address> Client::getAddress() {
    return this->address;
}

