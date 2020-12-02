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

@Named
@ApplicationScoped
@NoArgsConstructor
@Data
public class OrderManager {
    @Inject
    private Repository<Order> orderRepository;

    public void addOrder(Order order) {
        try {
            this.orderRepository.add(order);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
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
        return this.orderRepository.getAll();
    }

    public List<Order> getAllOrdersForTheClient(User user) {
        List<Order> orders = new ArrayList<>();
        for (Order order : this.orderRepository.getAll()) {
            if (order.getClient().equals(user)) {
                orders.add(order);
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

    public Order createOrder(List<Good> goods, Client user) throws OrderException {
        return new Order(LocalDateTime.now(), goods, user);
    }




}
