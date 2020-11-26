#include <boost/test/unit_test.hpp>
#include "General/Address.h"
#include "General/Client.h"
#include "ClientType/ClientTypeGold.h"
#include "ClientType/ClientTypePremium.h"
#include "ClientType/ClientTypeDefault.h"
#include "ClientType/ClientTypeBusiness.h"

BOOST_AUTO_TEST_SUITE(TestSuiteCorrectClient)

    BOOST_AUTO_TEST_CASE(AddreessTest) {
        std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("Bortnyka","18"));
        std::shared_ptr<Client> client = std::shared_ptr<Client>(new Client("Artur","Radiuk","226452",address));
        BOOST_CHECK_EQUAL(client->getAddress(),address);
        BOOST_CHECK_EQUAL(address->getNumber(),"18");
        BOOST_CHECK_EQUAL(address->getStreet(),"Bortnyka");
        BOOST_TEST_MESSAGE(client->getAddressData());

    }

    BOOST_AUTO_TEST_CASE(ClientTest) {
        std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("Bortnyka","18"));
        std::shared_ptr<Client> client = std::shared_ptr<Client>(new Client("Artur","Radiuk","226452",address));

        BOOST_CHECK_EQUAL(client->getFirstName(),"Artur");
        BOOST_CHECK_EQUAL(client->getLastName(),"Radiuk");
        BOOST_CHECK_EQUAL(client->getPersonalId(),"226452");
        BOOST_CHECK_EQUAL(client->getAddress(),address);
        BOOST_TEST_MESSAGE(client->toString());
    }
    BOOST_AUTO_TEST_CASE(ClientTypeTest) {
        std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("Bortnyka","18"));
        std::shared_ptr<Client> client = std::shared_ptr<Client>(new Client("Artur","Radiuk","226452",address));

        std::shared_ptr<ClientTypeDefault> clientTypeD=std::shared_ptr<ClientTypeDefault>(new ClientTypeDefault);
        BOOST_CHECK_EQUAL(client->getClientType()->toString(),clientTypeD->toString());

        client->changeClientType(4);
        std::shared_ptr<ClientTypePremium> clientTypeP=std::shared_ptr<ClientTypePremium>(new ClientTypePremium);
        BOOST_CHECK_EQUAL(client->getClientType()->toString(),clientTypeP->toString());

        client->changeClientType(2);
        std::shared_ptr<ClientTypeBusiness> clientTypeB=std::shared_ptr<ClientTypeBusiness>(new ClientTypeBusiness);
        BOOST_CHECK_EQUAL(client->getClientType()->toString(),clientTypeB->toString());

        client->changeClientType(3);
        std::shared_ptr<ClientTypeGold> clientTypeG=std::shared_ptr<ClientTypeGold>(new ClientTypeGold);
        BOOST_CHECK_EQUAL(client->getClientType()->toString(),clientTypeG->toString());

        client->changeClientType(88);
        BOOST_CHECK_EQUAL(client->getClientType()->toString(),clientTypeD->toString());

    }


BOOST_AUTO_TEST_SUITE_END()