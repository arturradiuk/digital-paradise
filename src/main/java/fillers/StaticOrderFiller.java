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
import java.util.concurrent.CopyOnWriteArrayList;

public class StaticOrderFiller implements DataFiller {
    @Override
    public List<Order> Fill() {
        List<Order> orders = new CopyOnWriteArrayList<>();

        UserRepository userRepository = new UserRepository();
        userRepository.initPeople();
        GoodRepository goodRepository = new GoodRepository();
        goodRepository.initGoods();


        List<Good> laptopLenovo = new ArrayList<>();
        Good laptopApple = null;
        Good laptopDell = null;
        Good laptopHP = null;
        for (Good g :
                goodRepository.getAll()) {
            if(g.getUuid().equals(UUID.fromString("14a6b7cc-bd4c-3022-83d0-d2af506bfb2b"))){
                laptopLenovo.add(g);
            }
            if(g.getUuid().equals(UUID.fromString("24a6b7cc-bd4c-3022-83d0-d2af506bfb2b"))){
                laptopApple = g;
            }
            if(g.getUuid().equals(UUID.fromString("34a6b7cc-bd4c-3022-83d0-d2af506bfb2b"))){
                laptopDell = g;
            }
            if(g.getUuid().equals(UUID.fromString("44a6b7cc-bd4c-3022-83d0-d2af506bfb2b"))){
                laptopHP= g;
            }

        }

        List<Good> tolaGoods = new ArrayList<>();
        tolaGoods.add(laptopApple);
        tolaGoods.add(laptopDell);

        User tola = null;
        for (User u : userRepository.getAll()) {
            if (u.getUuid().equals(UUID.fromString("1d6b6bd5-be82-3a41-87ac-5cd1b3b24756"))) {
                tola = u;
            }

        }
        try {
            orders.add(new Order(UUID.randomUUID(), LocalDateTime.now(), laptopLenovo, (Client) tola));
            orders.add(new Order(UUID.randomUUID(), LocalDateTime.now(), tolaGoods, (Client) tola));
            tolaGoods = new ArrayList<>();
            tolaGoods.add(laptopHP);
            orders.add(new Order(UUID.randomUUID(), LocalDateTime.now(), tolaGoods, (Client) tola));
        } catch (OrderException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
