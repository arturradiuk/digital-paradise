#ifndef COMPUTER_STORE_REPOSITORY_H
#define COMPUTER_STORE_REPOSITORY_H

#include <vector>
#include <string>
#include <memory>
template <class T>
class Repository {
public:
    virtual void add(std::shared_ptr<T> element) = 0;
    virtual void remove(std::shared_ptr<T> element) = 0;
    virtual std::vector<std::shared_ptr<T>> getAll() = 0;
    virtual std::string toString() = 0;
};

#endif //COMPUTER_STORE_REPOSITORY_H
