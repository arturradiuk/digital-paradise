#ifndef COMPUTER_STORE_ORDERMANAGER_H
#define COMPUTER_STORE_ORDERMANAGER_H

#include "memory"
#include "string"
class OrderRepository;
class Order;
class Client;
class ClientRepository;
class OrderUnaryPredicate;
class Good;

#include "General/Delivery.h"

class OrderManager {
    std::shared_ptr<OrderRepository> orderRepository;
public:
    OrderManager( std::shared_ptr<OrderRepository> orderRepository  = nullptr);

    std::shared_ptr<Order> findOrder(std::shared_ptr<OrderUnaryPredicate> unaryPredicate);

    virtual ~OrderManager();

    void addOrder(std::shared_ptr<Order> order);

    void createOrder(std::shared_ptr<Client> client, std::shared_ptr<Good> good,EnumDelivery enumDelivery,
                     double distance=0, int speed=0);

    void removeOrder(int position);
    void removeOrder(std::string dataStr);

    std::shared_ptr<Order> getOrder(int position);


};


#endif //COMPUTER_STORE_ORDERMANAGER_H
