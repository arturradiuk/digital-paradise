#include "General/DeliveryYES.h"

DeliveryYES::DeliveryYES(double distance, int deliverySpeed) : distance(distance), deliverySpeed(deliverySpeed)
{
}
double DeliveryYES::CalculatePrice() {
    return distance*deliverySpeed*2;
}
