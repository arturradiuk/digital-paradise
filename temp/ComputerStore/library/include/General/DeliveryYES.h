#ifndef COMPUTER_STORE_DELIVERYYES_H
#define COMPUTER_STORE_DELIVERYYES_H

class DeliveryYES {
    double distance;
    int deliverySpeed;
public:
    double CalculatePrice();
    DeliveryYES(double distance, int deliverySpeed);
};



#endif //COMPUTER_STORE_DELIVERYYES_H
