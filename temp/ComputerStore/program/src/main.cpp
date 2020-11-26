#include <iostream>
#include "Goods/Mouse.h"
#include "Goods/Pendrive.h"
#include "Goods/Keyboard.h"
#include "Goods/Tablet.h"
#include "General/Client.h"
#include "General/Address.h"
#include "memory"
#include "General/Order.h"
#include "Predicates/ClientUnaryPredicate.h"
#include "Managers/ClientManager.h"
#include "Repositories/ClientRepository.h"

#include "Managers/GoodManager.h"
#include "Repositories/GoodRepository.h"
#include "Predicates/GoodUnaryPredicate.h"

#include "Managers/OrderManager.h"
#include "Repositories/OrderRepository.h"
#include "Predicates/OrderUnaryPredicate.h"
#include "Goods/Computer.h"

using namespace std;

int main ()
{
    /*
//    Mouse mouse(20,1);
    std::shared_ptr<Mouse> mouse1 = std::shared_ptr<Mouse>(new Mouse(20,1));
    std::shared_ptr<Mouse> mouse2 = std::shared_ptr<Mouse>(new Mouse(20,1));
//    cout << mouse->toString()<<endl;
    //    Keyboard keyboard(10,1,1);
//    cout << keyboard.toString()<<endl;
//    Pendrive pendrive(30,64);
//    cout << pendrive.toString()<<endl;
//
//    Tablet tablet(100,16,128,9,15.0);
//    cout << tablet.toString()<<endl;


//    cout << endl ;
    std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("Bortnyka","18"));
    std::shared_ptr<Client> client = std::shared_ptr<Client>(new Client("Artur","Radiuk",
            "226452",address));
    client->changeClientType(4);
    EnumDelivery onPlace,delivery;
    std::shared_ptr<Order> order1 = std::shared_ptr<Order>(new Order(mouse1,delivery,2,2));
//    std::shared_ptr<Order> order2 = std::shared_ptr<Order>(new Order(mouse2,1,0,0));
    order1->setClient(client,order1);
//    order2->setClient(client,order2);

    cout << client->toString() <<endl<<endl;
    cout << order1->toString()<<endl;
*/

//    ClientUnaryPredicate* hz = new findClientInfo("d");

/*    std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("ulica","numer"));
    Client_shared_ptr first = Client_shared_ptr(new Client("Artur","Radiuk","226452", address));
    Client_shared_ptr second = Client_shared_ptr(new Client("Ar","R","226453", address));
    std::vector<Client_shared_ptr> repo;

    std::vector<Client_weak_ptr> repo2;


    repo.push_back(first);
    repo.push_back(second);


    repo2.push_back(first);
    repo2.push_back(second);

    ClientUnaryPredicate* f = new findClientPersonalID("226453");

    findClientPersonalID* F2 = dynamic_cast<findClientPersonalID*>(f);
    if(F2 == nullptr)
        cout <<endl<< " nie tym razem"<<endl;
    else cout <<endl<< "tym razem"<<endl;

    std::shared_ptr<ClientUnaryPredicate> ff = std::shared_ptr<ClientUnaryPredicate>(new findClientPersonalID("226453"));
    std::shared_ptr<findClientPersonalID> FF2 = std::dynamic_pointer_cast<findClientPersonalID>(ff);



//    std::vector<std::shared_ptr<Client>>::iterator it = std::find_if(repo.begin(),repo.end(), *F2);
    std::vector<std::weak_ptr<Client>>::iterator it = std::find_if(repo2.begin(),repo2.end(), *FF2);
    cout<<it[0].lock()->toString();*/

//How Client manager works.
    std::shared_ptr<Address> address = std::shared_ptr<Address>(new Address("ulica","numer"));
    Client_shared_ptr first = Client_shared_ptr(new Client("Art","Radiuk","22645", address));
    Client_shared_ptr second = Client_shared_ptr(new Client("Ar","R","226453", address));
    std::shared_ptr<ClientRepository> clientRep = std::shared_ptr<ClientRepository>(new ClientRepository());
    clientRep->add(first);
    clientRep->add(second);
    ClientManager clientMan(clientRep);
//    clientMan.removeClient(2);
//    clientMan.removeClient(1);
    std::shared_ptr<ClientUnaryPredicate> clUnar = std::shared_ptr<ClientUnaryPredicate>(new findClientPersonalID("226452"));

    clientMan.createClient("Artur", "Radiuk","226452",address); ////////////////creating
    if(clientMan.findClient(clUnar)!= nullptr)
    {
        cout<< clientMan.findClient(clUnar)->toString();
    }
    cout <<endl<<"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"<<endl;

//
    cout << endl << endl;
//How good manager works.
    std::shared_ptr<Mouse> mouse1 = std::shared_ptr<Mouse>(new Mouse('A',20,1));
    std::shared_ptr<Mouse> mouse2 = std::shared_ptr<Mouse>(new Mouse('A',30,0));

    std::shared_ptr<GoodRepository> goodRep = std::shared_ptr<GoodRepository>(new GoodRepository());
    goodRep->add(mouse1);
    goodRep->add(mouse2);
    GoodManager goodMan(goodRep, nullptr);

    std::shared_ptr<GoodUnaryPredicate> goUnar = std::shared_ptr<GoodUnaryPredicate>(new findGoodID(mouse2->getId()));
    cout << goodMan.findGood(goUnar)->toString()<<endl;
    goodMan.createGood('B',80,40); /////////////////////////////////////////creating
//    cout << goodMan.takeGood(3)->toString() << endl;
//
    cout << endl << endl;
//How order manager works.
    EnumDelivery onPlace,delivery;
    std::shared_ptr<Order> order1 = std::shared_ptr<Order>(new Order(mouse1,delivery,2,2));
    std::shared_ptr<Order> order2 = std::shared_ptr<Order>(new Order(mouse2,delivery,2,2));

    order1->setClient(first,order1);
    order2->setClient(second,order2);
    std::shared_ptr<OrderRepository> orderRep = std::shared_ptr<OrderRepository>(new OrderRepository());
    orderRep->add(order1);
    orderRep->add(order2);
    OrderManager orderMan(orderRep);
    std::shared_ptr<OrderUnaryPredicate> orUnar = std::shared_ptr<OrderUnaryPredicate>(new findOrderID(order2->getId()));
    cout << orderMan.findOrder(orUnar)->toString()<<endl;
//    orderMan.createOrder(clientMan.findClient(clUnar),goodMan.findGood(goUnar),onPlace,4,5);
//    cout<<endl<<orderMan.getOrder(3)->toString()<<endl;
//

    return 0;
}