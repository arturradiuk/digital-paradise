#ifndef COMPUTER_STORE_CLIENTTYPEGOLD_H
#define COMPUTER_STORE_CLIENTTYPEGOLD_H

#include "../General/ClientType.h"

class ClientTypeGold : public ClientType{
    double discount;
    double cashBack;
public:
    ClientTypeGold(double discount = 0.2,    double cashBack = 0.2);
    virtual ~ClientTypeGold();

    double getCashBack() override;

    std::string toString() override;

    double getDiscount() override;
};


#endif //COMPUTER_STORE_CLIENTTYPEGOLD_H
