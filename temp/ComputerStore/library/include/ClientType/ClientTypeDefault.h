#ifndef COMPUTER_STORE_CLIENTTYPEDEFAULT_H
#define COMPUTER_STORE_CLIENTTYPEDEFAULT_H

#include "../General/ClientType.h"
class ClientTypeDefault : public ClientType{
    double discount;
    double cashBack;
public:
    ClientTypeDefault(double discount = 0,    double cashBack =0  );
    virtual ~ClientTypeDefault();
    double getDiscount() override;

    double getCashBack() override;

    std::string toString() override;

};


#endif //COMPUTER_STORE_CLIENTTYPEDEFAULT_H
