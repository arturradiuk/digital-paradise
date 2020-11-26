#ifndef COMPUTER_STORE_KEYBOARD_H
#define COMPUTER_STORE_KEYBOARD_H

#include "Accessory.h"

class Keyboard : public Accessory {
    bool wireless;
    bool mechanical;
public:
    Keyboard(char rank, double price, bool wireless, bool mechanical);

    virtual ~Keyboard();

    bool isWireless() const;

    bool isMechanical() const;

    double getPrice() override;

    std::string toString() override;
};


#endif //COMPUTER_STORE_KEYBOARD_H
