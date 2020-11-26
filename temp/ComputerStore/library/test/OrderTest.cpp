#include <boost/test/unit_test.hpp>

#include "General/Order.h"

#include "General/Delivery.h"
#include "General/Good.h"
#include "Goods/Mouse.h"
#include "General/Client.h"
#include "General/Address.h"
#include "memory"
#include "Predicates/GoodUnaryPredicate.h"


BOOST_AUTO_TEST_SUITE(TestSuiteCorrectOrder)

    BOOST_AUTO_TEST_CASE(OrderTest) {
    std::shared_ptr<Good> mouse1 = std::shared_ptr<Mouse>(new Mouse('A',22,1));
    EnumDelivery onPlace,delivery;
//    std::shared_ptr<Order> order1 = std::shared_ptr<Order>(new Order(mouse1,delivery,2,2));
//    std::shared_ptr<Order> order2 = std::shared_ptr<Order>(new Order(mouse1,onPlace,2,2));
    std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("Bortnyka","18"));
    std::shared_ptr<Client> client = std::shared_ptr<Client>(new Client("Artur","Radiuk","226452",address));
//    order1->setClient(client,order1);
//    order2->setClient(client,order2);
//    BOOST_CHECK_EQUAL(order1->getFullPrice(),34.4);
    boost::local_time::time_zone_ptr timeZone{new boost::local_time::posix_time_zone("CET+1")};
    local_date_time_ptr date = local_date_time_ptr(new boost::local_time::local_date_time(boost::local_time::local_sec_clock::local_time(timeZone)));
//    BOOST_CHECK_EQUAL(order1->getOrderDateTime().date(),date->date());
//    BOOST_CHECK_EQUAL(order2->getFullPrice(),26.4);
//    BOOST_CHECK_EQUAL(order2->getGood(),mouse1);
//    BOOST_TEST_MESSAGE(order1->toString());
//    BOOST_TEST_MESSAGE(order2->toString());
//     BOOST_CHECK_THROW()
    }

BOOST_AUTO_TEST_SUITE_END()