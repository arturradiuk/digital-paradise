#include "Goods/PC.h"
#include "Exception/GoodException.h"

PC::PC(double price, int ram, int ssdCapacity, processor CPU, blockType block) : Computer(price, ram, ssdCapacity,
                                                                                          CPU),block(block) {
    if(ram <=256) throw GoodExeption("ram is less then 256 MB");
}

PC::~PC() {}

blockType PC::getBlockType() const {
    return block;
}

double PC::getPrice() {
    return Computer::getPrice() + (block+1)*500;
}

std::string PC::toString() {
    return Computer::toString() +
           "\nBlock Type: " + std::to_string(getBlockType()) + // problem is possible (enum)
           "\nPrice: " + std::to_string(getPrice());
}