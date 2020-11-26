#ifndef COMPUTER_STORE_GOODUNARYPREDICATE_H
#define COMPUTER_STORE_GOODUNARYPREDICATE_H

#include <boost/uuid/uuid.hpp>
#include <string>
#include <memory>

class Good;

class GoodUnaryPredicate {
public:
    GoodUnaryPredicate();
    virtual ~GoodUnaryPredicate();

    virtual bool operator()(std::weak_ptr<Good> good) = 0;
};

////////////////////////////////////////////////////////////////

class findGoodInfo : public GoodUnaryPredicate {
    std::string goodInfo;
public:
    explicit findGoodInfo(const std::string &goodInfo);

    bool operator()(std::weak_ptr<Good> good) override;

    ~findGoodInfo() override;
};

class findGoodID : public GoodUnaryPredicate {
    boost::uuids::uuid goodID;
public:
    explicit findGoodID(boost::uuids::uuid id);

    bool operator()(std::weak_ptr<Good> good) override;

    ~findGoodID() override;
};

#endif //COMPUTER_STORE_GOODUNARYPREDICATE_H
