#ifndef COMPUTER_STORE_GOODEXCEPTION_H
#define COMPUTER_STORE_GOODEXCEPTION_H

#include "stdexcept"
#include "string"

class GoodExeption : public std::logic_error {
public:
    GoodExeption(std::string);
};

#endif //COMPUTER_STORE_GOODEXCEPTION_H
