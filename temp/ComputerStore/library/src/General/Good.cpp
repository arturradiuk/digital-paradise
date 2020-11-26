#include "General/Good.h"
#include "Exception/GoodException.h"
#include <boost/uuid/uuid_generators.hpp>

Good::Good(double price) : price(price) {
            if(price<=0) throw GoodExeption("price is NAN field is empty!");
    this->id = boost::uuids::random_generator()();
}

Good::~Good() {
}

double Good::getPrice() {
    return price;
}

std::string Good::getIdStr(){
    return boost::uuids::to_string(getId());
}
uuid Good::getId(){
    return this->id;
}


std::string Good::toString() {
    return "ID: " + getIdStr();
}