package controller.managers;


import controller.exceptions.OrderException;
import controller.exceptions.RepositoryException;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.clients.Client;
import model.entities.User;
import model.entities.Good;
import model.entities.Order;
import model.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Named
@ApplicationScoped
@NoArgsConstructor
@Data
public class OrderManager {
    private UUID managerUuid = UUID.randomUUID();

    @Inject
    private Repository<Order, UUID> orderRepository;


    private Order createOrder(List<Good> goods, Client client) throws OrderException {

        for (Good g : goods) {
            g.setCount(g.getCount() - 1);
        }
        Order order = new Order(LocalDateTime.now(), goods, client);
        try {
            this.orderRepository.add(order);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        return order;
    }


    public Order createOrder(GoodManager goodManager, List<Good> goods, Client client) throws OrderException {
        for (Good g : goodManager.getAllGoods()) {

            for (Good gs : goods) {
                if (g.getUuid().equals(gs.getUuid())) {
                    g.setCount(g.getCount() - 1);
                }
            }

        }

        Order order = new Order(LocalDateTime.now(), goods, client);
        try {
            this.orderRepository.add(order);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        return order;

    }


    private void returnOrder(Order order) {

        for (Good g : order.getGoods()) {
            g.setCount(g.getCount() + 1);
        }
        this.removeOrder(order);

    }

    public void returnOrder(GoodManager goodManager, Order order) {
        this.removeOrder(order);
        for (Good g : goodManager.getAllGoods()) {

            for (Good og : order.getGoods()) {
                if (g.getUuid().equals(og.getUuid())) {
                    g.setCount(g.getCount() + 1);
                }
            }
        }
    }

    public void removeOrder(Order order) {
        try {
            this.orderRepository.remove(order);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }

    public Order getOrderByUUID(UUID uuid) {
        Order order = this.orderRepository.getAll().stream()
                .filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        return order;
    }

    public Order getOrderByOrderDateTime(LocalDateTime orderDateTime) {
        Order order = this.orderRepository.getAll().stream()
                .filter(c -> c.getOrderDateTime().equals(orderDateTime)).findFirst().orElse(null);
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = this.orderRepository.getAll();
        return orders;
    }

    public List<Order> getAllOrdersForTheUser(User user) {
        List<Order> orders = new CopyOnWriteArrayList<>();
        for (Order order : this.orderRepository.getAll()) {
            System.out.println(order);
//            if (order.getClient().equals(user)) {
            if (order.getClient().getUuid().equals(user.getUuid())) {
                orders.add(order);
                System.out.println(order);
            }
        }
        return orders;
    }

    public List<Order> getAllOrdersForTheGood(Good good) {
        List<Order> orders = new ArrayList<>();
        for (Order order : this.orderRepository.getAll()) {
            if (order.getGoods().contains(good)) {
                orders.add(order);
            }
        }

        return orders;
    }

//    public Order createOrder(List<Good> goods, Client user) throws OrderException {
//        return new Order(LocalDateTime.now(), goods, user);
//    }


}
