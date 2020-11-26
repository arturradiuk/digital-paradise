#include "Predicates/ClientUnaryPredicate.h"
#include "General/Client.h"

ClientUnaryPredicate::ClientUnaryPredicate() = default;

ClientUnaryPredicate::~ClientUnaryPredicate() = default;


findClientInfo::findClientInfo(const std::string &clientInfo) : clientInfo(clientInfo) {}

bool findClientInfo::operator()(std::weak_ptr<Client> client) {
    return clientInfo == client.lock()->toString();
}

findClientInfo::~findClientInfo() {}
//End findClientInfo

//findClientPersonalID
findClientPersonalID::findClientPersonalID(std::string personalID) : personalID(personalID) {}

bool findClientPersonalID::operator()(std::weak_ptr<Client> client) {
    return personalID == client.lock()->getPersonalId();
}

findClientPersonalID::~findClientPersonalID() = default;
//End findClientPersonalID

//findClientFirstName
findClientFirstName::findClientFirstName(std::string firstName) : firstName(firstName) {}

bool findClientFirstName::operator()(std::weak_ptr<Client> client) {
    return firstName == client.lock()->getFirstName();
}

findClientFirstName::~findClientFirstName() {}
//End findClientFirstName

//findClientLastName
findClientLastName::findClientLastName(std::string lastName):lastName(lastName) {}

bool findClientLastName::operator()(std::weak_ptr<Client> client) {
    return lastName == client.lock()->getLastName();
}

findClientLastName::~findClientLastName() {}
//End findClientLastName