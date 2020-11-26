#ifndef COMPUTER_STORE_CLIENTEXCEPTION_H
#define COMPUTER_STORE_CLIENTEXCEPTION_H

#include "stdexcept"
#include "string"

class ClientExeption : public std::logic_error {
public:
    ClientExeption(std::string);
};

#endif //COMPUTER_STORE_CLIENTEXCEPTION_H
