#include "General/Address.h"

Address::Address(std::string street, std::string number) : street(street), number(number) {}

Address::~Address() {

}
std::string Address::toString() {
    return "Address: street = "+this->street+", house number = "+this->number;
}

const std::string &Address::getStreet() const {
    return street;
}

const std::string &Address::getNumber() const {
    return number;
}
