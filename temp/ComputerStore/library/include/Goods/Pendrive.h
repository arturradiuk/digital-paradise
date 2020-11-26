#ifndef COMPUTER_STORE_PENDRIVE_H
#define COMPUTER_STORE_PENDRIVE_H

#include "Accessory.h"

class Pendrive : public Accessory{
    int capacity;
public:
    Pendrive(char rank, double price, int capacity);
    ~Pendrive() override;

    double getPrice() override;
    int getCapacity() const;

    std::string toString() override;
};

#endif //COMPUTER_STORE_PENDRIVE_H
