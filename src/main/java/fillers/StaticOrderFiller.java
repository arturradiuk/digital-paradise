package fillers;


import controller.exceptions.OrderException;
import model.clients.Client;
import model.entities.Good;
import model.entities.Order;
import model.entities.User;
import model.repositories.GoodRepository;
import model.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class StaticOrderFiller implements DataFiller {
    @Override
    public List<Order> Fill() {
        List<Order> orders = new ArrayList<>();

        UserRepository userRepository = new UserRepository();
        userRepository.initPeople();
        GoodRepository goodRepository = new GoodRepository();
        goodRepository.initGoods();

        List<Good> laptopLenovo = goodRepository.getAll().stream().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("1234567890_good").getBytes()))).collect(Collectors.toList());
        Good laptopApple = goodRepository.getAll().stream().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("2234567890_good").getBytes()))).collect(Collectors.toList()).get(0);
        Good laptopDell = goodRepository.getAll().stream().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("4234567890_good").getBytes()))).collect(Collectors.toList()).get(0);

        List<Good> tolaGoods = new ArrayList<>();
        tolaGoods.add(laptopApple);
        tolaGoods.add(laptopDell);

        Optional<User> tola = userRepository.getAll().stream().findAny().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("1234567890_person").getBytes())));
//        Optional<Person> lolek = personRepository.getAll().stream().findAny().filter(good -> good.getUuid().equals(UUID.nameUUIDFromBytes(new String("3234567890_person").getBytes())));
        System.out.println(userRepository.toString());
        try {
            orders.add(new Order(LocalDateTime.now(),laptopLenovo,(Client)tola.get()));
            orders.add(new Order(LocalDateTime.now(),tolaGoods,(Client)tola.get()));
        } catch (OrderException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
