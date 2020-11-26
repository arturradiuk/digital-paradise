#ifndef COMPUTER_STORE_DELIVERY_H
#define COMPUTER_STORE_DELIVERY_H

enum EnumDelivery: int {
    delivery,onPlace
};

class Delivery {
    EnumDelivery isDelivery;
    double distance;
    int deliverySpeed;
    double price;
private:
    void setPrice(int price);
public:
    double getDeliveryPrice();

    virtual ~Delivery();

    Delivery(EnumDelivery isDelivery, double distance, int deliverySpeed);



};



#endif //COMPUTER_STORE_DELIVERY_H
