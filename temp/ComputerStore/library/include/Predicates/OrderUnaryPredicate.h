#ifndef COMPUTER_STORE_ORDERUNARYPREDICATE_H
#define COMPUTER_STORE_ORDERUNARYPREDICATE_H

#include <boost/uuid/uuid.hpp>
#include <string>
#include <memory>

class Order;

class OrderUnaryPredicate {
public:
    OrderUnaryPredicate();
    virtual bool operator()(std::weak_ptr<Order> order) = 0;
    virtual ~OrderUnaryPredicate();
};
////////////////////////////////////////////////////////////////

class findOrderInfo : public OrderUnaryPredicate {
    std::string orderInfo;
public:
    explicit findOrderInfo(const std::string &orderInfo);

    bool operator()(std::weak_ptr<Order> order) override;

    virtual ~findOrderInfo() override;
};

class findOrderID : public OrderUnaryPredicate {
    boost::uuids::uuid orderID;

public:
    explicit findOrderID(boost::uuids::uuid id);

    bool operator()(std::weak_ptr<Order> order) override;

    virtual ~findOrderID() override;
};


#endif //COMPUTER_STORE_ORDERUNARYPREDICATE_H
