#include "Goods/Laptop.h"

Laptop::Laptop(double price, int ram, int ssdCapacity, processor cpu, double screenSize, int usbAmount, bool camera)
        : Computer(price, ram, ssdCapacity, cpu), screenSize(screenSize), camera(camera), USBAmount(usbAmount) {}

Laptop::~Laptop() {}

double Laptop::getScreenSize() const {
    return screenSize;
}

bool Laptop::isCamera() const {
    return camera;
}

int Laptop::getUSBAmount() const {
    return USBAmount;
}

double Laptop::getPrice() {
    return Computer::getPrice() + screenSize*10 + USBAmount*100 + (camera ? 200 : 0);
}

std::string Laptop::toString() {
    return Computer::toString() +
           "\nScreen Size: " + std::to_string(getScreenSize()) +
           "\nCamera: " + std::to_string(isCamera()) +
           "\nUSB Amount: " + std::to_string(getUSBAmount()) +
           "\nPrice: " + std::to_string(getPrice());
}