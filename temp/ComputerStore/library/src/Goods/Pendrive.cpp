#include "Goods/Pendrive.h"

Pendrive::Pendrive(char rank, double price, int capacity) : Accessory(rank, price), capacity(capacity) {}

Pendrive::~Pendrive() {}

double Pendrive::getPrice() {
    switch (capacity)
    {
        case 16:
            return Accessory::getPrice()*0.1+Accessory::getPrice();
        case 32:
            return Accessory::getPrice()*0.2+Accessory::getPrice();
        case 64:
            return Accessory::getPrice()*0.3+Accessory::getPrice();
        case 128:
            return Accessory::getPrice()*0.4+Accessory::getPrice();
    }
}

int Pendrive::getCapacity() const {
    return capacity;
}

std::string Pendrive::toString() {
    return Accessory::toString() +
           "\nCapacity: " + std::to_string(getCapacity()) +
           "\nPrice: " + std::to_string(getPrice());
}