#include "Predicates/OrderUnaryPredicate.h"
#include "General/Order.h"

OrderUnaryPredicate::OrderUnaryPredicate() = default;

OrderUnaryPredicate::~OrderUnaryPredicate() = default;

//findOrderInfo
findOrderInfo::findOrderInfo(const std::string &orderInfo) : orderInfo(orderInfo) {}

bool findOrderInfo::operator()(std::weak_ptr<Order> order) {
    return orderInfo == order.lock()->toString();
}

findOrderInfo::~findOrderInfo() = default;
//End findOrderInfo

//findOrderID
findOrderID::findOrderID(boost::uuids::uuid id) : orderID(id) {}

bool findOrderID::operator()(std::weak_ptr<Order> order) {
    return orderID == order.lock()->getId();
}

findOrderID::~findOrderID() = default;
//End findOrderID
