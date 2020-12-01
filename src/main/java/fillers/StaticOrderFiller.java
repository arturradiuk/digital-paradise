package fillers;


import controller.exceptions.OrderException;
import controller.managers.PersonManager;
import model.entities.Good;
import model.entities.Order;
import model.entities.Person;
import model.repositories.GoodRepository;
import model.repositories.PersonRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class StaticOrderFiller implements DataFiller {
    @Override
    public List<Order> Fill() {
        List<Order> orders = new ArrayList<>();

        PersonRepository personRepository = new PersonRepository();
        personRepository.initPeople();
        GoodRepository goodRepository = new GoodRepository();
        goodRepository.initGoods();

        List<Good> laptopLenovo = goodRepository.getAll().stream().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("1234567890_good").getBytes()))).collect(Collectors.toList());
        List<Good> laptopApple = goodRepository.getAll().stream().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("2234567890_good").getBytes()))).collect(Collectors.toList());

        Optional<Person> tola = personRepository.getAll().stream().findAny().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("1234567890_person").getBytes())));
//        Optional<Person> lolek = personRepository.getAll().stream().findAny().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("3234567890_person").getBytes())));
        System.out.println(personRepository.toString());
        try {
            orders.add(new Order(LocalDateTime.now(),laptopLenovo,tola.get()));
            orders.add(new Order(LocalDateTime.now(),laptopApple,tola.get()));
        } catch (OrderException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
