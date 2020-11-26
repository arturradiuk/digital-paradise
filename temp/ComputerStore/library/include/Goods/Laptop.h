#ifndef COMPUTER_STORE_LAPTOP_H
#define COMPUTER_STORE_LAPTOP_H

#include "Computer.h"

class Laptop : public Computer {
    double screenSize;
    bool camera;
    int USBAmount;
public:
    Laptop(double price, int ram, int ssdCapacity, processor cpu, double screenSize,int usbAmount, bool camera = false);
    virtual ~Laptop();

    double getPrice() override;
    double getScreenSize() const;
    bool isCamera() const;
    int getUSBAmount() const;

    std::string toString() override;
};

#endif //COMPUTER_STORE_LAPTOP_H
