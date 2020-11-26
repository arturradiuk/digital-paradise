#ifndef COMPUTER_STORE_GOODMANAGER_H
#define COMPUTER_STORE_GOODMANAGER_H


#include "memory"
#include "string"

#include "Goods/Computer.h"
#include "Goods/PC.h"

class GoodRepository;
class Good;
class GoodUnaryPredicate;


class GoodManager {
    std::shared_ptr<GoodRepository> availableGoodRepository;
    std::shared_ptr<GoodRepository> soldGoodRepository;
public:
    std::shared_ptr<Good> findGood(std::shared_ptr<GoodUnaryPredicate> unaryPredicate);

    GoodManager(std::shared_ptr<GoodRepository> availableGoodRepository = nullptr,
                std::shared_ptr<GoodRepository> soldGoodRepository = nullptr);

    virtual ~GoodManager();

    void addGood(std::shared_ptr<Good> good);


    void createGood(double price, int ram, int ssdCapacity, processor cpu); //Computer
    void createGood(char rank,double price, bool wireless, bool mechanical); //Keyboard
    void createGood(double price, int ram, int ssdCapacity, processor cpu, double screenSize,
                    int usbAmount, bool camera = false); // Laptop
    void createGood(char rank,double price, bool wireless); //Mouse
    void createGood(double price, int ram, int ssdCapacity,
                    processor CPU, blockType block);  // PC
    void createGood(char rank,double price, int capacity); // Pendrive
    void createGood(double price, int ram, int ssdCapacity,
                    processor CPU, double screenSize); //Tablet

    std::shared_ptr<Good> takeGood(int position);
    std::shared_ptr<Good> takeGood(std::string dataStr);
    void removeGood(int position);
    void removeGood(std::string dataStr);
    std::shared_ptr<Good> getGood(int position);


};


#endif //COMPUTER_STORE_GOODMANAGER_H
