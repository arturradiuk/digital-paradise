#include <boost/test/unit_test.hpp>

#include "Managers/ClientManager.h"
#include "Managers/GoodManager.h"
#include "Managers/OrderManager.h"

#include "Repositories/ClientRepository.h"
#include "Repositories/GoodRepository.h"
#include "Repositories/OrderRepository.h"

#include "General/Client.h"
#include "General/Order.h"

#include "General/Delivery.h"

#include "Goods/Mouse.h"
#include "General/Address.h"

#include "Predicates/ClientUnaryPredicate.h"
#include "Predicates/GoodUnaryPredicate.h"
#include "Predicates/OrderUnaryPredicate.h"

BOOST_AUTO_TEST_SUITE(TestSuiteCorrectManager)

    std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("ulica","numer"));
    Client_shared_ptr first = Client_shared_ptr(new Client("Art","Radiuk","22645", address));
    Client_shared_ptr second = Client_shared_ptr(new Client("Ar","R","226453", address));
    std::shared_ptr<ClientRepository> clientRep = std::shared_ptr<ClientRepository>(new ClientRepository());

    std::shared_ptr<Mouse> mouse1 = std::shared_ptr<Mouse>(new Mouse('A',20,1));
    std::shared_ptr<Mouse> mouse2 = std::shared_ptr<Mouse>(new Mouse('A',30,0));
    std::shared_ptr<GoodRepository> goodRep = std::shared_ptr<GoodRepository>(new GoodRepository());

    EnumDelivery onPlace,delivery;
    std::shared_ptr<Order> order1 = std::shared_ptr<Order>(new Order(mouse1,delivery,2,2));
    std::shared_ptr<Order> order2 = std::shared_ptr<Order>(new Order(mouse2,delivery,2,2));
    std::shared_ptr<OrderRepository> orderRep = std::shared_ptr<OrderRepository>(new OrderRepository());



    BOOST_AUTO_TEST_CASE(ClientManagerTest) {

        clientRep->add(first);
        clientRep->add(second);
        ClientManager clientMan(clientRep);
        clientMan.createClient("Artur", "Radiuk","226452",address); ////////////////creating
        std::shared_ptr<ClientUnaryPredicate> clUnar1 = std::shared_ptr<ClientUnaryPredicate>(new findClientPersonalID("226452"));
        BOOST_CHECK_EQUAL(clientMan.findClient(clUnar1)->getPersonalId(),"226452");
        BOOST_CHECK_EQUAL(clientMan.findClient(clUnar1)->getPersonalId(),"226452");
        std::shared_ptr<ClientUnaryPredicate> clUnar2 = std::shared_ptr<ClientUnaryPredicate>(new findClientPersonalID("226453"));
        clientMan.removeClient(2);
        BOOST_CHECK_EQUAL(clientMan.findClient(clUnar2), nullptr);
        clientMan.addClient(second);
        BOOST_CHECK_EQUAL(clientMan.findClient(clUnar2), second);

    }
    BOOST_AUTO_TEST_CASE(GoodManagerTest) {

        goodRep->add(mouse1);
        goodRep->add(mouse2);
        GoodManager goodMan(goodRep);
//
        std::shared_ptr<GoodUnaryPredicate> goUnar2 = std::shared_ptr<GoodUnaryPredicate>(new findGoodID(mouse2->getId()));
        std::shared_ptr<GoodUnaryPredicate> goUnar1 = std::shared_ptr<GoodUnaryPredicate>(new findGoodID(mouse1->getId()));
        BOOST_CHECK_EQUAL(goodMan.findGood(goUnar2),mouse2);
        BOOST_CHECK_EQUAL(goodMan.findGood(goUnar1),mouse1);
//
        goodMan.createGood('A',80,40);
        std::shared_ptr<GoodUnaryPredicate> goUnar3 = std::shared_ptr<GoodUnaryPredicate>(new findGoodID(goodMan.takeGood(3)->getId()));
        BOOST_CHECK_EQUAL(goodMan.findGood(goUnar3), nullptr);
//
        goodMan.removeGood(2);
        BOOST_CHECK_EQUAL(goodMan.findGood(goUnar2), nullptr);
        goodMan.addGood(mouse2);
        BOOST_CHECK_EQUAL(goodMan.findGood(goUnar2),mouse2);


    }


    BOOST_AUTO_TEST_CASE(OrderManagerTest) {

        order1->setClient(first,order1);
        order2->setClient(second,order2);
        orderRep->add(order1);
        orderRep->add(order2);
        OrderManager orderMan(orderRep);
        std::shared_ptr<OrderUnaryPredicate> orUnar1 = std::shared_ptr<OrderUnaryPredicate>(new findOrderID(order1->getId()));
        std::shared_ptr<OrderUnaryPredicate> orUnar2 = std::shared_ptr<OrderUnaryPredicate>(new findOrderID(order2->getId()));
        BOOST_CHECK_EQUAL(orderMan.findOrder(orUnar1),order1);
        BOOST_CHECK_EQUAL(orderMan.findOrder(orUnar2),order2);
        orderMan.createOrder(first,mouse2,onPlace);
        BOOST_CHECK_EQUAL(        orderMan.getOrder(3)->getGood(),mouse2);
    }

BOOST_AUTO_TEST_SUITE_END()
