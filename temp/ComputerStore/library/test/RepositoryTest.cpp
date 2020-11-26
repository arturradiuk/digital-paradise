#include <boost/test/unit_test.hpp>

#include "Repositories/ClientRepository.h"
#include "Repositories/GoodRepository.h"
#include "Repositories/OrderRepository.h"
#include "General/Client.h"
#include "General/Address.h"
#include "General/Order.h"
#include "Goods/Keyboard.h"
#include "Goods/Mouse.h"
#include "Goods/Pendrive.h"
#include "Goods/PC.h"
#include "Goods/Laptop.h"
#include "Goods/Tablet.h"

BOOST_AUTO_TEST_SUITE(TestSuiteCorrectRepository)

    BOOST_AUTO_TEST_CASE(RepositoryTest) {
        ClientRepository crep;

        Address_ptr address(new Address("Al. Politechniki","1"));

        Client_shared_ptr c1(new Client("Jan","Kowalski","1234567890",address));
        Client_shared_ptr c2(new Client("Krzysztof","Brzeszczecikiewicz","0987654321",address));

        crep.add(c1);
        crep.add(c2);

        std::vector<std::shared_ptr<Client>> clients;

        clients.push_back(c1);
        clients.push_back(c2);

        BOOST_CHECK(crep.getAll() == clients);

        crep.remove(c1);
        clients.erase(clients.begin());

        BOOST_CHECK(crep.getAll() == clients);

        BOOST_TEST_MESSAGE(crep.toString());
    }

BOOST_AUTO_TEST_SUITE_END()

