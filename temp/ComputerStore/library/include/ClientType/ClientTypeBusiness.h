#ifndef COMPUTER_STORE_CLIENTTYPEBUSINESS_H
#define COMPUTER_STORE_CLIENTTYPEBUSINESS_H

#include "../General/ClientType.h"
class ClientTypeBusiness : public ClientType{
    double discount;
    double cashBack;
public:
    ClientTypeBusiness(double discount = 0.1, double cashBack = 0.1);
    virtual ~ClientTypeBusiness();
    double getDiscount() override;
    double getCashBack()override;

    std::string toString() override;

};

#endif //COMPUTER_STORE_CLIENTTYPEBUSINESS_H
