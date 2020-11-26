#include "Goods/Mouse.h"

Mouse::Mouse(char rank, double price, bool wireless) : Accessory(rank, price), wireless(wireless) {}

Mouse::~Mouse() {

}

double Mouse::getPrice() {
    return Accessory::getPrice() + (wireless ? Accessory::getPrice()*0.2 : 0);
}

bool Mouse::isWireless() const {
    return wireless;
}

std::string Mouse::toString() {
    return Accessory::toString() +
           "\nWireless: " + std::to_string(isWireless()) +
           "\nPrice: " + std::to_string(getPrice());
}