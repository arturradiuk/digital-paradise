#ifndef COMPUTER_STORE_ORDERREPOSITORY_H
#define COMPUTER_STORE_ORDERREPOSITORY_H

#include "General/Order.h"
#include "General/Repository.h"

class OrderRepository : public Repository<Order> {
    std::vector<Order_shared_ptr> orders;
public:
public:
    void add(Order_shared_ptr order) override;
    void remove(Order_shared_ptr order) override;
    std::vector<Order_shared_ptr> getAll() override;

    std::string toString() override;
};

#endif //COMPUTER_STORE_ORDERREPOSITORY_H
