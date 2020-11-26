#ifndef COMPUTER_STORE_PC_H
#define COMPUTER_STORE_PC_H

#include "Goods/Computer.h"

enum blockType {DeepCool, Frime, GameMax};

class PC: public Computer {
    blockType block;
public:
    PC(double price, int ram, int ssdCapacity, processor CPU, blockType block);
    virtual ~PC();

    blockType getBlockType() const;
    double getPrice() override;

    std::string toString() override;
};

#endif //COMPUTER_STORE_PC_H
