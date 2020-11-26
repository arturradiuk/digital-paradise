#include "Goods/Accessory.h"
#include "Exception/GoodException.h"
Accessory::Accessory(char rank, double price) : Good(price), rank(rank)
{
    if(rank==' ') throw GoodExeption("rank is NAS!");
}

Accessory::~Accessory() {

}

double Accessory::getPrice() {
    double percent = rank == 'D' ? 0.7: rank == 'C' ? 0.5 : rank == 'B' ? 0.3 : 0;

    return Good::getPrice() + Good::getPrice()*percent;
}

char Accessory::getRank() {
    return rank;
}

std::string Accessory::toString() {
    return Good::toString() +
           "\nRank: " + getRank();
}