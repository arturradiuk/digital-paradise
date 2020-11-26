#ifndef COMPUTER_STORE_GOOD_H
#define COMPUTER_STORE_GOOD_H

#include <boost/uuid/uuid.hpp> // need to create uuid - unique identifier
#include <boost/uuid/uuid_io.hpp>         // streaming operators and to_string.
#include <memory>
#include <string>

typedef boost::uuids::uuid uuid;

class Good {
    double price;
    uuid  id;
public:
    Good(double price);
    virtual ~Good();
    virtual double getPrice();
    std::string getIdStr();
    uuid getId();
    virtual std::string toString();
};

typedef std::shared_ptr<Good> Good_shared_ptr;
typedef std::weak_ptr<Good> Good_weak_ptr;

#endif //COMPUTER_STORE_GOOD_H
