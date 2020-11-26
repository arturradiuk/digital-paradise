#include "ClientType/ClientTypePremium.h"

ClientTypePremium::ClientTypePremium(double discount,    double cashBack
) : discount(discount),cashBack(cashBack) {}

ClientTypePremium::~ClientTypePremium() {

}

double ClientTypePremium::getDiscount() {
    return discount;
}

double ClientTypePremium::getCashBack()  {
    return cashBack;
}

std::string ClientTypePremium::toString() {
    return "Premium: discount = "+std::to_string(this->discount)+", client = "+std::to_string(this->cashBack);

}