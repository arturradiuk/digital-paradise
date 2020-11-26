#ifndef COMPUTER_STORE_MOUSE_H
#define COMPUTER_STORE_MOUSE_H

#include "Accessory.h"

class Mouse : public Accessory{
    bool wireless;
public:
    Mouse(char rank, double price, bool wireless);
    virtual ~Mouse();

    bool isWireless() const;

    double getPrice() override;

    std::string toString() override;

};

#endif //COMPUTER_STORE_MOUSE_H
