#include "Goods/Computer.h"
#include <iostream>
Computer::Computer(double price, int ram, int ssdCapacity, processor cpu) : Good(price), RAM(ram),
                                                                            SSDCapacity(ssdCapacity),
                                                                            CPU(cpu) {}

Computer::~Computer() {}

double Computer::getPrice() {
    return Good::getPrice() + (RAM/1024.0)*50 + (SSDCapacity/1024.0)*150 + (CPU+1)*1000;
}

int Computer::getRAM(){
    return RAM;
}

int Computer::getSSDCapacity() {
    return SSDCapacity;
}

processor Computer::getProcessor(){
    return CPU;
}

std::string Computer::toString() {
    return Good::toString() +
           "\nRAM: "+ std::to_string(getRAM()) +
           "\nSSD Capacity: " + std::to_string(getSSDCapacity()) +
           "\nProcessor: " + std::to_string(getProcessor()); // problem is possible (enum)
}