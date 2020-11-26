#include "Repositories/GoodRepository.h"

void GoodRepository::add(Good_shared_ptr good) {
    goods.push_back(good);
}

void GoodRepository::remove(Good_shared_ptr good) {
    for (unsigned int i = 0; i < goods.size(); i++)
//        if (goods[i].lock() == good)
        if (goods[i] == good)
            goods.erase(goods.begin()+i);
}

std::vector<Good_shared_ptr> GoodRepository::getAll() {
    return goods;
}

std::string GoodRepository::toString() {
    std::string res;

    for (auto &&i : goods)
        res += i->toString() + "\n";

    return res;
}

