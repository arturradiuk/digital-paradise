

#include "General/Delivery.h"
#include "General/DeliveryYES.h"
#include "General/DeliveryNo.h"

double Delivery::getDeliveryPrice() {
    return this->price;
}

Delivery::Delivery(EnumDelivery isDelivery, double distance, int deliverySpeed) : isDelivery(isDelivery),
                                                                                  distance(distance),
                                                                                  deliverySpeed(deliverySpeed)
{
    EnumDelivery delivery;
    if(isDelivery==delivery)
    {
        DeliveryYES* deliveryYes = new DeliveryYES(this->distance,this->deliverySpeed);
        this->price=deliveryYes->CalculatePrice();
        delete deliveryYes;
    }else{
        DeliveryNo* deliveryNO = new DeliveryNo();
        setPrice(deliveryNO->calculatePrice());
        delete deliveryNO;

    }

}

Delivery::~Delivery() {

}

void Delivery::setPrice( int price) {
    this->price=price;
}

