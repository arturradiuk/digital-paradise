#include "ClientType/ClientTypeDefault.h"

ClientTypeDefault::ClientTypeDefault(double discount,    double cashBack )
        : discount(discount), cashBack(cashBack) {}

ClientTypeDefault::~ClientTypeDefault() {

}

double ClientTypeDefault::getDiscount() {
    return discount;
}

double ClientTypeDefault::getCashBack()  {
    return cashBack;
}

std::string ClientTypeDefault::toString() {
    return "Default: discount = "+std::to_string(this->discount)+", client = "+std::to_string(this->cashBack);

}
