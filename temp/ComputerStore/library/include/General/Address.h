#ifndef COMPUTER_STORE_ADDRESS_H
#define COMPUTER_STORE_ADDRESS_H

#include <string>
#include <memory>

class Address {
    std::string street;
    std::string number;
public:
    Address(std::string street, std::string number);

    virtual ~Address();
    std::string toString();

    const std::string &getStreet() const;

    const std::string &getNumber() const;

};

typedef std::shared_ptr<Address> Address_ptr;


#endif //COMPUTER_STORE_ADDRESS_H
