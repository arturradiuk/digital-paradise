#include "ClientType/ClientTypeBusiness.h"

ClientTypeBusiness::ClientTypeBusiness(double discount,    double cashBack) : discount(discount), cashBack(cashBack) {}

ClientTypeBusiness::~ClientTypeBusiness() {

}

double ClientTypeBusiness::getDiscount() {
    return this->discount;
}

double ClientTypeBusiness::getCashBack() {
    return cashBack;
}

std::string ClientTypeBusiness::toString() {
    return "Business: discount = "+std::to_string(this->discount)+", client = "+std::to_string(this->cashBack);
}
