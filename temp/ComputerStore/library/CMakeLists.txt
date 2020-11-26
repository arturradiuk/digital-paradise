cmake_minimum_required(VERSION 3.4)
project(Library)

set(SOURCE_FILES
        include/General/Good.h
        include/General/Order.h
        include/General/Client.h
        include/General/ClientType.h
        include/General/Address.h
        include/General/Repository.h
        include/General/Delivery.h
        include/General/DeliveryYES.h
        include/General/DeliveryNo.h

        src/General/Good.cpp
        src/General/Order.cpp
        src/General/Client.cpp
        src/General/ClientType.cpp
        src/General/Address.cpp
        src/General/Delivery.cpp
        src/General/DeliveryNo.cpp
        src/General/DeliveryYES.cpp


        include/Goods/Accessory.h
        include/Goods/Computer.h

        src/Goods/Accessory.cpp
        src/Goods/Computer.cpp


        include/Goods/Keyboard.h
        include/Goods/Laptop.h
        include/Goods/Mouse.h
        include/Goods/PC.h
        include/Goods/Pendrive.h
        include/Goods/Tablet.h

        src/Goods/Keyboard.cpp
        src/Goods/Laptop.cpp
        src/Goods/Mouse.cpp
        src/Goods/PC.cpp
        src/Goods/Pendrive.cpp
        src/Goods/Tablet.cpp


        include/ClientType/ClientTypeDefault.h
        include/ClientType/ClientTypeBusiness.h
        include/ClientType/ClientTypeGold.h
        include/ClientType/ClientTypePremium.h

        src/ClientType/ClientTypeDefault.cpp
        src/ClientType/ClientTypeBusiness.cpp
        src/ClientType/ClientTypeGold.cpp
        src/ClientType/ClientTypePremium.cpp


        include/Exception/ClientException.h
        include/Exception/OrderException.h
        include/Exception/GoodException.h

        src/Exception/ClientException.cpp
        src/Exception/OrderException.cpp
        src/Exception/GoodException.cpp


        include/Managers/ClientManager.h
        include/Managers/OrderManager.h
        include/Managers/GoodManager.h

        src/Managers/ClientManager.cpp
        src/Managers/OrderManager.cpp
        src/Managers/GoodManager.cpp


        include/Repositories/ClientRepository.h
        include/Repositories/GoodRepository.h
        include/Repositories/OrderRepository.h

        src/Repositories/ClientRepository.cpp
        src/Repositories/GoodRepository.cpp
        src/Repositories/OrderRepository.cpp


        include/Predicates/ClientUnaryPredicate.h
        include/Predicates/GoodUnaryPredicate.h
        include/Predicates/OrderUnaryPredicate.h

        src/Predicates/ClientUnaryPredicate.cpp
        src/Predicates/GoodUnaryPredicate.cpp
        src/Predicates/OrderUnaryPredicate.cpp
        )

#utwórz bibliotekę typu STATIC, SHARED albo MODULE ze wskazanych źródeł
add_library(Library ${SOURCE_FILES})

target_include_directories(Library PUBLIC include)

find_package (Boost 1.60.0 COMPONENTS "unit_test_framework" REQUIRED)

include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}/include
        ${Boost_INCLUDE_DIRS}
)

set(SOURCE_TEST_FILES
        test/master.cpp
        test/ClientTest.cpp
        test/OrderTest.cpp

        include/General/Good.h
        include/General/Order.h
        include/General/Client.h
        include/General/ClientType.h
        include/General/Address.h
        include/General/Repository.h
        include/General/Delivery.h
        include/General/DeliveryYES.h
        include/General/DeliveryNo.h

        src/General/Good.cpp
        src/General/Order.cpp
        src/General/Client.cpp
        src/General/ClientType.cpp
        src/General/Address.cpp
        src/General/Delivery.cpp
        src/General/DeliveryNo.cpp
        src/General/DeliveryYES.cpp


        include/Goods/Accessory.h
        include/Goods/Computer.h

        src/Goods/Accessory.cpp
        src/Goods/Computer.cpp


        include/Goods/Keyboard.h
        include/Goods/Laptop.h
        include/Goods/Mouse.h
        include/Goods/PC.h
        include/Goods/Pendrive.h
        include/Goods/Tablet.h

        src/Goods/Keyboard.cpp
        src/Goods/Laptop.cpp
        src/Goods/Mouse.cpp
        src/Goods/PC.cpp
        src/Goods/Pendrive.cpp
        src/Goods/Tablet.cpp


        include/ClientType/ClientTypeDefault.h
        include/ClientType/ClientTypeBusiness.h
        include/ClientType/ClientTypeGold.h
        include/ClientType/ClientTypePremium.h

        src/ClientType/ClientTypeDefault.cpp
        src/ClientType/ClientTypeBusiness.cpp
        src/ClientType/ClientTypeGold.cpp
        src/ClientType/ClientTypePremium.cpp


        include/Exception/ClientException.h
        include/Exception/OrderException.h
        include/Exception/GoodException.h

        src/Exception/ClientException.cpp
        src/Exception/OrderException.cpp
        src/Exception/GoodException.cpp


        include/Managers/ClientManager.h
        include/Managers/OrderManager.h
        include/Managers/GoodManager.h

        src/Managers/ClientManager.cpp
        src/Managers/OrderManager.cpp
        src/Managers/GoodManager.cpp


        include/Repositories/ClientRepository.h
        include/Repositories/GoodRepository.h
        include/Repositories/OrderRepository.h

        src/Repositories/ClientRepository.cpp
        src/Repositories/GoodRepository.cpp
        src/Repositories/OrderRepository.cpp


        include/Predicates/ClientUnaryPredicate.h
        include/Predicates/GoodUnaryPredicate.h
        include/Predicates/OrderUnaryPredicate.h

        src/Predicates/ClientUnaryPredicate.cpp
        src/Predicates/GoodUnaryPredicate.cpp
        src/Predicates/OrderUnaryPredicate.cpp
        test/GoodTest.cpp test/RepositoryTest.cpp test/ManagerTest.cpp test/ExceptionTest.cpp)

add_executable (TestLib ${SOURCE_TEST_FILES})

#dołącz bibliotekę z testowanymi klasami do programu testów
target_link_libraries (TestLib
        Library
        ${Boost_UNIT_TEST_FRAMEWORK_LIBRARY}
        )

#dodaj test TestBiblioteki1. Instrukcji cmake add_test może być wiele.
#Dodatkowe parametry wpływają na szczegółowość generowanego raportu. Standardowo znajduje się on w Testing/Temporary.
add_test(TestLibrary1 TestLib
        --report_level=detailed
        --log_level=all
        --color_output=yes
        )

#Aby raport testów był szczegółowo wypisany na konsolę,
#można dodać własny cel budowy o nazwie np. check (make check) w postaci:
add_custom_target(check ${CMAKE_COMMAND} -E env CTEST_OUTPUT_ON_FAILURE=1 BOOST_TEST_LOG_LEVEL=all
        ${CMAKE_CTEST_COMMAND} -C $<CONFIGURATION> --verbose
        WORKING_DIRECTORY ${CMAKE_BINARY_DIR})