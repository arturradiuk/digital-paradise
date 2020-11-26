#ifndef COMPUTER_STORE_TABLET_H
#define COMPUTER_STORE_TABLET_H

#include "Computer.h"

class Tablet : public Computer{
    double screenSize;
public:
    Tablet(double price, int ram, int ssdCapacity, processor CPU, double screenSize);

    virtual ~Tablet();

    double getPrice() override;
    double getScreenSize() const;

    std::string toString() override;
};

#endif //COMPUTER_STORE_TABLET_H
