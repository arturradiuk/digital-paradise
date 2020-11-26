#ifndef COMPUTER_STORE_ORDEREXCEPTION_H
#define COMPUTER_STORE_ORDEREXCEPTION_H

#include "stdexcept"
#include "string"

class OrderExeption : public std::logic_error {
public:
    OrderExeption(std::string);
};



#endif //COMPUTER_STORE_ORDEREXCEPTION_H
