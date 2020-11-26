#ifndef COMPUTER_STORE_GOODREPOSITORY_H
#define COMPUTER_STORE_GOODREPOSITORY_H

#include "General/Good.h"
#include "General/Repository.h"

class GoodRepository : public Repository<Good> {
    std::vector<Good_shared_ptr> goods;
public:
    void add(Good_shared_ptr good) override;
    void remove(Good_shared_ptr good) override;
    std::vector<Good_shared_ptr> getAll() override;

    std::string toString() override;
};

#endif //COMPUTER_STORE_GOODREPOSITORY_H
