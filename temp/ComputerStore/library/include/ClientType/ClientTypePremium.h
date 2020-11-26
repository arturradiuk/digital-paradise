#ifndef COMPUTER_STORE_CLIENTTYPEPREMIUM_H
#define COMPUTER_STORE_CLIENTTYPEPREMIUM_H

#include "../General/ClientType.h"

class ClientTypePremium : public ClientType{
    double discount;
    double cashBack;
public:
    ClientTypePremium(double discount=0.4,    double cashBack = 0.4 );

    double getDiscount() override;

    double getCashBack() override;

    std::string toString() override;

    virtual ~ClientTypePremium();

};


#endif //COMPUTER_STORE_CLIENTTYPEPREMIUM_H
