#ifndef COMPUTER_STORE_CLIENTTYPE_H
#define COMPUTER_STORE_CLIENTTYPE_H

#include "string"

#include "string"

class ClientType {
public:
    virtual ~ClientType();
    virtual double getDiscount() = 0;
    virtual double getCashBack() = 0;
    virtual std::string toString() = 0;
};

#endif //COMPUTER_STORE_CLIENTTYPE_H
