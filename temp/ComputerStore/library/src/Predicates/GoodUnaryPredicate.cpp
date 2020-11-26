#include "Predicates/GoodUnaryPredicate.h"
#include "General/Good.h"

GoodUnaryPredicate::GoodUnaryPredicate() = default;

GoodUnaryPredicate::~GoodUnaryPredicate() = default;

//findGoodInfo
findGoodInfo::findGoodInfo(const std::string &goodInfo) : goodInfo(goodInfo) {}

bool findGoodInfo::operator()(Good_weak_ptr  good) {
    return goodInfo == good.lock()->toString();
}

findGoodInfo::~findGoodInfo() {}
//End findGoodInfo

//findGoodID
findGoodID::findGoodID(uuid id) : goodID(id) {}

bool findGoodID::operator()(Good_weak_ptr  good) {
    return  to_string(goodID) == good.lock()->getIdStr();

}

findGoodID::~findGoodID() {}
//End findGoodID