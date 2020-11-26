#ifndef COMPUTER_STORE_ORDER_H
#define COMPUTER_STORE_ORDER_H

#include "string"
#include <memory>

#include <boost/uuid/uuid.hpp> // need to create uuid - unique identifier
#include <boost/date_time/local_time/local_time.hpp> // need to create rentalDateTime
#include <boost/uuid/uuid_io.hpp>         // streaming operators and to_string.
#include <boost/uuid/uuid_generators.hpp> // for generator

class Client;
class Order;
class Good;
class Delivery;
enum EnumDelivery:int;

typedef boost::uuids::uuid uuid;
typedef boost::local_time::local_date_time* local_date_time_ptr;
typedef boost::local_time::time_zone_ptr time_zone_ptr;
typedef boost::local_time::local_date_time local_date_time;
typedef boost::local_time::local_time_period local_time_period;

class Order {
    uuid id;
    local_date_time_ptr orderDateTime;
    std::shared_ptr<Good> good;
    Delivery* delivery;
    std::shared_ptr<Client> client;

public:
    Order(std::shared_ptr<Good> good, EnumDelivery enumDelivery,double distance=0,int speed=0);
    virtual ~Order();
    void setClient(std::shared_ptr<Client> client, std::shared_ptr<Order> order);
    double getFullPrice();
    std::string toString();
    local_date_time getOrderDateTime();
    uuid getId();
    std::shared_ptr<Good> getGood();
    Delivery getDelivery();
};

typedef std::shared_ptr<Order> Order_shared_ptr;
typedef std::weak_ptr<Order> Order_weak_ptr;


#endif //COMPUTER_STORE_ORDER_H
