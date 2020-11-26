#include "Goods/Tablet.h"

Tablet::Tablet(double price, int ram, int ssdCapacity, processor CPU, double screenSize) : Computer(price, ram,
                                                                                                    ssdCapacity,
                                                                                                    CPU),
                                                                                           screenSize(screenSize) {}

Tablet::~Tablet() {}

double Tablet::getScreenSize() const {
    return screenSize;
}

double Tablet::getPrice() {
    if(screenSize<=9.7)
        return Computer::getPrice()*0.1+Computer::getPrice();

    if(screenSize<=10.1)
        return Computer::getPrice()*0.3+Computer::getPrice();

    return Computer::getPrice()*0.5+Computer::getPrice();
}

std::string Tablet::toString() {
    return Computer::toString() +
           "\nScreen Size: " + std::to_string(getScreenSize()) +
           "\nPrice: " + std::to_string(getPrice());
}