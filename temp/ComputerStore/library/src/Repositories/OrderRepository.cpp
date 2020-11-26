#include "Repositories/OrderRepository.h"

void OrderRepository::add(Order_shared_ptr order) {
    orders.push_back(order);
}

void OrderRepository::remove(Order_shared_ptr order) {
    for (unsigned int i = 0; i < orders.size(); i++)
        if (orders[i] == order)
            orders.erase(orders.begin()+i);
}

std::vector<Order_shared_ptr> OrderRepository::getAll() {
    return orders;
}

std::string OrderRepository::toString() {
    std::string res;

    for (auto &&i : orders)
        res += i->toString() + "\n";

    return res;
}
