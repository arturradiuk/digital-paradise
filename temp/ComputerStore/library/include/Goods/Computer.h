#ifndef COMPUTER_STORE_COMPUTER_H
#define COMPUTER_STORE_COMPUTER_H

#include "General/Good.h"

enum processor {AMD_Athlon, Intel_Xeon, AMD_Ryzen,Intel_Core};



class Computer : public Good {
    int RAM;
    int SSDCapacity;
    processor CPU;
public:
    Computer(double price, int ram, int ssdCapacity, processor cpu);
    virtual ~Computer();

    double getPrice() override;
    virtual int getRAM();
    virtual int getSSDCapacity();
    virtual processor getProcessor();

    std::string toString() override;
};

#endif //COMPUTER_STORE_COMPUTER_H
