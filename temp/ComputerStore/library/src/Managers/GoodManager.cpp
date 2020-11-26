#include "Managers/GoodManager.h"
#include "vector"
#include "General/Good.h"
#include "Repositories/GoodRepository.h"
#include "Predicates/GoodUnaryPredicate.h"

#include <boost/uuid/uuid_io.hpp>
#include <boost/lexical_cast.hpp>

#include "Goods/PC.h"
#include "Goods/Pendrive.h"
#include "Goods/Mouse.h"
#include "Goods/Keyboard.h"
#include "Goods/Computer.h"
#include "Goods/Tablet.h"
#include "Goods/Laptop.h"
#include "Exception/GoodException.h"

GoodManager::GoodManager( std::shared_ptr<GoodRepository> availableGoodRepository,
                          std::shared_ptr<GoodRepository> soldGoodRepository) : availableGoodRepository(
        availableGoodRepository), soldGoodRepository(soldGoodRepository)
{
    // try and catch????????
    if(this->availableGoodRepository== nullptr)
    {
        this->availableGoodRepository = std::shared_ptr<GoodRepository>(new GoodRepository());
    }

    if(this->soldGoodRepository== nullptr)
    {
        this->soldGoodRepository = std::shared_ptr<GoodRepository>(new GoodRepository());
    }
}

GoodManager::~GoodManager() {

}

std::shared_ptr<Good> GoodManager::findGood(std::shared_ptr<GoodUnaryPredicate> unaryPredicate) {
    std::vector<Good_shared_ptr> temp_repo;
    temp_repo = this->availableGoodRepository->getAll();

    std::shared_ptr<findGoodID> F_ID = std::dynamic_pointer_cast<findGoodID>(unaryPredicate);
    if(F_ID!=nullptr)
    {
        std::vector<std::shared_ptr<Good>>::iterator it = std::find_if(temp_repo.begin(),temp_repo.end(), *F_ID);
        if(it!=temp_repo.end()){
            return it[0];
        }
        return nullptr;
    }

    std::shared_ptr<findGoodInfo> F_Info = std::dynamic_pointer_cast<findGoodInfo>(unaryPredicate);
    if(F_Info!=nullptr)
    {
        std::vector<std::shared_ptr<Good>>::iterator it = std::find_if(temp_repo.begin(),temp_repo.end(), *F_Info);
        if(it!=temp_repo.end()){
            return it[0];
        }
        return nullptr;
    }


    return nullptr;
}

void GoodManager::addGood(std::shared_ptr<Good> good)
{
    std::vector<Good_shared_ptr> temp = this->availableGoodRepository->getAll();
    for (auto f:temp)
    {
        if(f->toString()==good->toString())throw GoodExeption("there is the same element in repo")      ;
    }
    this->availableGoodRepository->add(good);

}

std::shared_ptr<Good> GoodManager::takeGood(int position)
{
    std::vector<Good_shared_ptr> temp = this->availableGoodRepository->getAll();
    std::shared_ptr<Good> tempGood;
    std::weak_ptr<Good> tempGoodW;

    if(position-1>=temp.size())throw " there is no so many clients";
    tempGood = temp[position-1];


    this->availableGoodRepository->remove(tempGood);
    this->soldGoodRepository->add(tempGood);
    return tempGood;
}

std::shared_ptr<Good> GoodManager::takeGood(std::string id)
{
    std::shared_ptr<GoodUnaryPredicate> temp = std::shared_ptr<GoodUnaryPredicate>(
            new findGoodID(boost::lexical_cast<uuid>(id)));
    std::shared_ptr<Good> goodTemp = this->findGood(temp);
    this->availableGoodRepository->remove(goodTemp);
    this->soldGoodRepository->add(goodTemp);
    return goodTemp;
}

void GoodManager::removeGood(int position)
{
    std::vector<Good_shared_ptr> temp = this->availableGoodRepository->getAll();
    std::shared_ptr<Good> tempGood;
    if(position-1>=temp.size())throw " there is no so many clients";
    tempGood = temp[position-1];
    this->availableGoodRepository->remove(tempGood);
}

void GoodManager::removeGood(std::string id)
{
    std::shared_ptr<GoodUnaryPredicate> temp = std::shared_ptr<GoodUnaryPredicate>(
            new findGoodID(boost::lexical_cast<uuid>(id)));
    std::shared_ptr<Good> goodTemp = this->findGood(temp);
    this->availableGoodRepository->remove(goodTemp);
}

void GoodManager::createGood(double price, int ram, int ssdCapacity, processor cpu)
{
    this->addGood(std::shared_ptr<Good>(new Computer(price,ram,ssdCapacity,cpu)));
}

void GoodManager::createGood(char rank,double price, bool wireless, bool mechanical)
{
    this->addGood(std::shared_ptr<Good>(new Keyboard(rank,price,wireless,mechanical)));

}

void GoodManager::createGood(double price, int ram, int ssdCapacity, processor cpu, double screenSize, int usbAmount,
                             bool camera)
{
    this->addGood(std::shared_ptr<Good>(new Laptop(price,ram,ssdCapacity,cpu,screenSize,usbAmount,camera)));
}

void GoodManager::createGood(char rank,double price, bool wireless)
{
    this->addGood(std::shared_ptr<Good>(new Mouse(rank,price,wireless)));
}

void GoodManager::createGood(double price, int ram, int ssdCapacity, processor CPU, blockType block)
{
    this->addGood(std::shared_ptr<Good>(new PC(price,ram,ssdCapacity,CPU,block)));
}

void GoodManager::createGood(char rank,double price, int capacity)
{

    std::shared_ptr<Good> temp = std::shared_ptr<Good>(new Pendrive(rank,price,capacity));
    this->addGood(temp);
}

void GoodManager::createGood(double price, int ram, int ssdCapacity, processor CPU, double screenSize)
{
    this->addGood(std::shared_ptr<Good>(new Tablet(price,ram,ssdCapacity,CPU,screenSize)));
}

std::shared_ptr<Good> GoodManager::getGood(int position)
{
    std::vector<Good_shared_ptr> temp = this->availableGoodRepository->getAll();
    std::shared_ptr<Good> tempGood;
    if(position-1>=temp.size())throw " there is no so many clients";
    tempGood = temp[position-1];
    return tempGood;
}
