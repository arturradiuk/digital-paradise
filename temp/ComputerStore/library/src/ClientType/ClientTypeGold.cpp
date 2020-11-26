#include "ClientType/ClientTypeGold.h"

ClientTypeGold::ClientTypeGold(double discount,    double cashBack) :
        discount(discount), cashBack(cashBack) {}

ClientTypeGold::~ClientTypeGold() {

}

double ClientTypeGold::getDiscount() {
    return discount;
}

double ClientTypeGold::getCashBack()  {
    return cashBack;
}

std::string ClientTypeGold::toString() {
    return "Gold: discount = "+std::to_string(this->discount)+", client = "+std::to_string(this->cashBack);

}