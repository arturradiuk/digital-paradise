

#include "Managers/OrderManager.h"
#include "General/Order.h"
#include "Repositories/OrderRepository.h"
#include "Repositories/ClientRepository.h"
#include "Predicates/OrderUnaryPredicate.h"

#include <boost/uuid/uuid_io.hpp>
#include <boost/lexical_cast.hpp>
#include "Exception/OrderException.h"


OrderManager::OrderManager(std::shared_ptr<OrderRepository> orderRepository) : orderRepository(orderRepository)
{
    if(this->orderRepository== nullptr)
        orderRepository=std::shared_ptr<OrderRepository>(new OrderRepository());

}

std::shared_ptr<Order> OrderManager::findOrder(std::shared_ptr<OrderUnaryPredicate> unaryPredicate) {
    std::vector<Order_shared_ptr> temp_repo;
    temp_repo = this->orderRepository->getAll();

    std::shared_ptr<findOrderID> F_ID = std::dynamic_pointer_cast<findOrderID>(unaryPredicate);
    if(F_ID!=nullptr)
    {
        std::vector<std::shared_ptr<Order>>::iterator it = std::find_if(temp_repo.begin(),temp_repo.end(), *F_ID);
        if(it!=temp_repo.end()){
            return it[0];
        }
        return nullptr;
    }

    std::shared_ptr<findOrderInfo> F_Info = std::dynamic_pointer_cast<findOrderInfo>(unaryPredicate);
    if(F_Info!=nullptr)
    {
        std::vector<std::shared_ptr<Order>>::iterator it = std::find_if(temp_repo.begin(),temp_repo.end(), *F_Info);
        if(it!=temp_repo.end()){
            return it[0];
        }
        return nullptr;
    }
    return nullptr;
}

OrderManager::~OrderManager() {

}

void OrderManager::addOrder(std::shared_ptr<Order> order)
{
    std::vector<Order_shared_ptr> temp = this->orderRepository->getAll();
    for (auto f:temp)
    {
        if(f->toString()==order->toString())throw OrderExeption("there is the same element in repo")      ;
    }
    this->orderRepository->add( order);
}

void OrderManager::createOrder(std::shared_ptr<Client> client, std::shared_ptr<Good> good,EnumDelivery enumDelivery,
                               double distance, int speed)
{
    std::shared_ptr<Order> temp  = std::shared_ptr<Order>(new Order(good,enumDelivery,distance,speed));
    temp->setClient(client,temp);
    this->addOrder(temp);
}

void OrderManager::removeOrder(int position)
{
    std::vector<Order_shared_ptr> temp = this->orderRepository->getAll();
    std::shared_ptr<Order> tempOrder;
    if(position-1>=temp.size())throw " there is no so many orders";
    tempOrder = temp[position-1];
    this->orderRepository->remove(tempOrder);
}

void OrderManager::removeOrder(std::string id)
{
    std::shared_ptr<OrderUnaryPredicate> temp = std::shared_ptr<OrderUnaryPredicate>(new findOrderID(
            boost::lexical_cast<uuid>(id)
    ));
    this->orderRepository->remove(this->findOrder(temp));
}

std::shared_ptr<Order> OrderManager::getOrder(int position)
{
    std::vector<Order_shared_ptr> temp = this->orderRepository->getAll();
    std::shared_ptr<Order> tempOrder;
    if(position-1>=temp.size())throw " there is no so many orders";
    tempOrder = temp[position-1];
    return tempOrder;
}
