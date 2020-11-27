package controller.managers;


import controller.exceptions.OrderException;
import controller.exceptions.RepozytoryException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.entities.Client;
import model.entities.Good;
import model.entities.Order;
import model.repositories.OrderRepository;
import model.repositories.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class OrderManager {
    private Repository<Order> orderRepository;

    public void addOrder(Order order) {
        try {
            this.orderRepository.add(order);
        } catch (RepozytoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }

    public void removeOrder(Order order) {
        try {
            this.orderRepository.remove(order);
        } catch (RepozytoryException e) { // todo handle exception
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

    public List<Order> getAllOrdersForTheClient(Client client) {
        List<Order> orders = new ArrayList<>();
        for (Order order : this.orderRepository.getAll()) {
            if (order.getClient().equals(client)) {
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

    public Order createOrder(List<Good> goods, Client client) throws OrderException {
        if (goods.stream().anyMatch(good -> good.getCount() < 0)) {
            throw new OrderException("Cannot create an order");
        }
        goods.stream().forEach(good -> good.setBasePrice(good.getCount() - 1));
        return new Order(LocalDateTime.now(), goods, client);
    }


}
