#ifndef COMPUTER_STORE_CLIENTUNARYPREDICATE_H
#define COMPUTER_STORE_CLIENTUNARYPREDICATE_H

#include <string>
#include <memory>

class Client;

class ClientUnaryPredicate {
public:
    ClientUnaryPredicate();
    virtual ~ClientUnaryPredicate();

    virtual bool operator()(std::weak_ptr<Client> client) = 0;
};
////////////////////////////////////////////////////////////////

class findClientInfo : public ClientUnaryPredicate {
    std::string clientInfo;
public:
    explicit findClientInfo(const std::string &clientInfo);
    bool operator()(std::weak_ptr<Client> client) override;
    ~findClientInfo() override;
};

class findClientPersonalID : public ClientUnaryPredicate {
    std::string personalID;
public:
    explicit findClientPersonalID(std::string personalID);
    bool operator()(std::weak_ptr<Client> client) override;
    ~findClientPersonalID() override;
};

class findClientFirstName : public ClientUnaryPredicate {
    std::string firstName;
public:
    explicit findClientFirstName(std::string firstName);
    bool operator()(std::weak_ptr<Client> client) override;
    ~findClientFirstName() override;
};

class findClientLastName : public ClientUnaryPredicate {
    std::string lastName;
public:
    explicit findClientLastName(std::string lastName);
    bool operator()(std::weak_ptr<Client> client) override;
    ~findClientLastName() override;
};

#endif //COMPUTER_STORE_CLIENTUNARYPREDICATE_H
