
#include "General/Order.h"
#include "General/Client.h"
#include "General/Good.h"
#include "General/ClientType.h"
#include "General/Delivery.h"
#include "Exception/OrderException.h"
#include "sstream"

using namespace std;

Order::Order(std::shared_ptr<Good> good, EnumDelivery enumDelivery,double distance,int speed)
        : good(good){
    boost::local_time::time_zone_ptr timeZone{new boost::local_time::posix_time_zone("CET+1")};
    this->orderDateTime = local_date_time_ptr(new boost::local_time::local_date_time(boost::local_time::local_sec_clock::local_time(timeZone)));
    this->id = boost::uuids::random_generator()();
    this->delivery = new Delivery(enumDelivery,distance,speed);
    if(good == nullptr ) throw OrderExeption("good = nullptr");
    EnumDelivery delivery;
    if(enumDelivery==delivery and distance==0 and speed==0) throw OrderExeption("error delivery distance =0, and speed =0");
}

void Order::setClient(std::shared_ptr<Client> client, std::shared_ptr<Order> order) {
    if(client== nullptr)throw "blad";
    this->client = std::shared_ptr<Client>(client);
    this->client->setOrder(order);
}

local_date_time Order::getOrderDateTime(){
    return *orderDateTime;
}

uuid Order::getId(){
    return id;
}

std::shared_ptr<Good> Order::getGood() {
    return good;
}

Order::~Order() {
    delete this->delivery;
}

double Order::getFullPrice() {
    delivery->getDeliveryPrice();
    this->good->getPrice();
    this->client->getClientType()->getDiscount();
    return this->good->getPrice()-this->client->getClientType()->getDiscount()*this->good->getPrice()+
    delivery->getDeliveryPrice();
}

std::string Order::toString() {
    return "Order: ID = "+ boost::uuids::to_string(this->id)+
           ";\n   Client: First Name = "+this->client->getFirstName()+", Last Name = "+this->client->getLastName()+", Personal Id = "+this->client->getPersonalId()+
           ";\n   " + this->client->getAddressData()+
           "\n   " + this->client->getClientTypeData()+
           "\n   " + this->good->toString()+
           "\n   Delivery price = " + std::to_string(this->delivery->getDeliveryPrice())+
           "\n   Total price = "+std::to_string(this->getFullPrice());
}

Delivery Order::getDelivery() {
    return *this->delivery;
}

