#include "Goods/Keyboard.h"

Keyboard::Keyboard(char rank, double price, bool wireless, bool mechanical) : Accessory(rank,price), wireless(wireless),
                                                                   mechanical(mechanical) {}

Keyboard::~Keyboard() {}

double Keyboard::getPrice() {
    double tempPrice=0;
    if(wireless)
        tempPrice+=Accessory::getPrice()*0.2;
    if(mechanical)
        tempPrice+=Accessory::getPrice()*0.2;
    return tempPrice+Accessory::getPrice();
}

bool Keyboard::isWireless() const {
    return wireless;
}

bool Keyboard::isMechanical() const {
    return mechanical;
}

std::string Keyboard::toString() {
    return Accessory::toString() +
           "\nWireless: " + std::to_string(isWireless()) +
           "\nMechanical: " + std::to_string(isMechanical()) +
           "\nPrice: " + std::to_string(getPrice());
}