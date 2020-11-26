#ifndef COMPUTER_STORE_ACCESSORY_H
#define COMPUTER_STORE_ACCESSORY_H

#include "../General/Good.h"

class Accessory : public Good{
    char rank;
public:
    Accessory(char rank, double price);
    virtual ~Accessory();

    char getRank();
    double getPrice() override;

    std::string toString() override;
};


#endif //COMPUTER_STORE_ACCESSORY_H
