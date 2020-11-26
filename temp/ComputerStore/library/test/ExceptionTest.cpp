#include <boost/test/unit_test.hpp>

#include "General/Client.h"
#include "General/Order.h"

#include "General/Delivery.h"

#include "Goods/Mouse.h"
#include "Goods/PC.h"
#include "Predicates/GoodUnaryPredicate.h"

#include "Exception/ClientException.h"
#include "Exception/GoodException.h"
#include "Exception/OrderException.h"
#include "General/Address.h"

BOOST_AUTO_TEST_SUITE(TestSuiteCorrectException)

    BOOST_AUTO_TEST_CASE(ClientExeptionTest) {
        std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("Bortnyka","18"));
        BOOST_CHECK_THROW(Client_shared_ptr (new Client("fr","ls","2", nullptr)),ClientExeption);
        BOOST_CHECK_THROW(Client_shared_ptr (new Client("","ls","2", address)),ClientExeption);
        BOOST_CHECK_THROW(Client_shared_ptr (new Client("fr","","2", address)),ClientExeption);
        BOOST_CHECK_THROW(Client_shared_ptr (new Client("fr","ls","", address)),ClientExeption);
    }
 BOOST_AUTO_TEST_CASE(GoodExeptionTest) {
    processor AMD_Athlon, Intel_Xeon, AMD_Ryzen,Intel_Core;
    blockType DeepCool, Frime, GameMax;

        BOOST_CHECK_THROW(        Good_shared_ptr(new Good(-1)),GoodExeption);
        BOOST_CHECK_THROW(        Good_shared_ptr(new Mouse(' ',3,0)),GoodExeption);
        BOOST_CHECK_THROW(        Good_shared_ptr(new PC(3,128,45,Intel_Core,Frime)),GoodExeption);
    }
    BOOST_AUTO_TEST_CASE(OrderExeptionTest) {
        EnumDelivery delivery;
        BOOST_CHECK_THROW(        Order_shared_ptr(new Order(nullptr, delivery,3,4)),OrderExeption);
        auto good = Good_shared_ptr (new Mouse('A',3,0));
    }

BOOST_AUTO_TEST_SUITE_END()

