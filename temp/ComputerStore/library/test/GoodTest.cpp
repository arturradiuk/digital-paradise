#include <boost/test/unit_test.hpp>

#include "Goods/Keyboard.h"
#include "Goods/Mouse.h"
#include "Goods/Pendrive.h"
#include "Goods/PC.h"
#include "Goods/Laptop.h"
#include "Goods/Tablet.h"

BOOST_AUTO_TEST_SUITE(TestSuiteCorrectGood)

    BOOST_AUTO_TEST_CASE(GoodTest) {
        std::shared_ptr<Keyboard> keyboard(new Keyboard('A',100,true,true));

        BOOST_CHECK_EQUAL(keyboard->getRank(),'A');
        BOOST_CHECK_EQUAL(keyboard->isWireless(),true);
        BOOST_CHECK_EQUAL(keyboard->isMechanical(),true);
        BOOST_CHECK_CLOSE(keyboard->getPrice(),140,0.0000001);
        BOOST_TEST_MESSAGE(keyboard->toString());


        std::shared_ptr<Mouse> mouse(new Mouse('D',50,false));
        BOOST_CHECK_EQUAL(mouse->getRank(),'D');
        BOOST_CHECK_EQUAL(mouse->isWireless(),false);
        BOOST_CHECK_CLOSE(mouse->getPrice(),85,0.0000001);
        BOOST_TEST_MESSAGE(keyboard->toString());

        std::shared_ptr<Pendrive> pendrive(new Pendrive('B',150,64));
        BOOST_CHECK_EQUAL(pendrive->getRank(),'B');
        BOOST_CHECK_EQUAL(pendrive->getCapacity(),64);
        BOOST_CHECK_CLOSE(pendrive->getPrice(),253.5,0.0000001);
        BOOST_TEST_MESSAGE(pendrive->toString());

        std::shared_ptr<PC> pc(new PC(500,4096,256,processor::Intel_Xeon,blockType::Frime));

        BOOST_CHECK_EQUAL(pc->getRAM(),4096);
        BOOST_CHECK_EQUAL(pc->getSSDCapacity(),256);
        BOOST_CHECK_EQUAL(pc->getProcessor(),processor::Intel_Xeon);
        BOOST_CHECK_EQUAL(pc->getBlockType(),blockType::Frime);
        BOOST_CHECK_CLOSE(pc->getPrice(),3737.5,0.0000001);
        BOOST_TEST_MESSAGE(pc->toString());


        std::shared_ptr<Laptop> laptop(new Laptop(300,2048,512,processor::Intel_Core,15.4,6));
        BOOST_CHECK_EQUAL(laptop->getRAM(),2048);
        BOOST_CHECK_EQUAL(laptop->getSSDCapacity(),512);
        BOOST_CHECK_EQUAL(laptop->getProcessor(),processor::Intel_Core);
        BOOST_CHECK_EQUAL(laptop->getScreenSize(),15.4);
        BOOST_CHECK_EQUAL(laptop->isCamera(),false);
        BOOST_CHECK_CLOSE(laptop->getPrice(),5229,0.0000001);
        BOOST_TEST_MESSAGE(laptop->toString());


        std::shared_ptr<Tablet> tablet(new Tablet(200,512,128,processor::AMD_Ryzen,10.5));
        BOOST_CHECK_EQUAL(tablet->getRAM(),512);
        BOOST_CHECK_EQUAL(tablet->getSSDCapacity(),128);
        BOOST_CHECK_EQUAL(tablet->getProcessor(),processor::AMD_Ryzen);
        BOOST_CHECK_EQUAL(tablet->getScreenSize(),10.5);
        BOOST_CHECK_CLOSE(tablet->getPrice(),4865.625,0.0000001);
        BOOST_TEST_MESSAGE(tablet->toString());
    }

BOOST_AUTO_TEST_SUITE_END()

