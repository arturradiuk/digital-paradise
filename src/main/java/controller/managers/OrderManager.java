package controller.managers;


import controller.exceptions.OrderException;
import controller.exceptions.repository.RepositoryException;
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

    @Inject
    private Repository<Order, UUID> orderRepository;


    private Order createOrder(List<Good> goods, Client client) throws OrderException, RepositoryException {

        for (Good g : goods) {
            g.setCount(g.getCount() - 1);
        }
        Order order = new Order(LocalDateTime.now(), goods, client);
        this.orderRepository.add(order);

        return order;
    }


    public Order createOrder(GoodManager goodManager, List<Good> goods, Client client) throws OrderException, RepositoryException {
        for (Good g : goodManager.getAllGoods()) {

            for (Good gs : goods) {
                if (g.getUuid().equals(gs.getUuid())) {
                    g.setCount(g.getCount() - 1);
                }
            }

        }

        Order order = new Order(LocalDateTime.now(), goods, client);
        this.orderRepository.add(order);

        return order;

    }


    private void returnOrder(Order order) throws RepositoryException {

        for (Good g : order.getGoods()) {
            g.setCount(g.getCount() + 1);
        }
        this.removeOrder(order);

    }

    public void returnOrder(GoodManager goodManager, Order order) throws RepositoryException {
        this.removeOrder(order);
        for (Good g : goodManager.getAllGoods()) {

            for (Good og : order.getGoods()) {
                if (g.getUuid().equals(og.getUuid())) {
                    g.setCount(g.getCount() + 1);
                }
            }
        }
    }

    public void removeOrder(Order order) throws RepositoryException {
        this.orderRepository.remove(order);
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
