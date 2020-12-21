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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Named
@ApplicationScoped
@NoArgsConstructor
@Data
public class OrderManager implements IManager<Order, UUID> {

    @Inject
    private Repository<Order, UUID> orderRepository;

    @Override
    public void add(Order element) throws RepositoryException { // todo here must be exception
        this.orderRepository.add(element);
    }

    @Override
    public void update(UUID id, Order element) throws RepositoryException {
        this.orderRepository.update(id, element);
    }

    @Override
    public void remove(Order order) throws RepositoryException {
        this.orderRepository.remove(order);
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = this.orderRepository.getAll();
        return orders;
    }

    public Order createOrder(GoodManager goodManager, List<Good> goods, Client client) throws OrderException, RepositoryException {
        for (Good g : goodManager.getAll()) {

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

    public void returnOrder(GoodManager goodManager, Order order) throws RepositoryException {
        this.remove(order);
        for (Good g : goodManager.getAll()) {

            for (Good og : order.getGoods()) {
                if (g.getUuid().equals(og.getUuid())) {
                    g.setCount(g.getCount() + 1);
                }
            }
        }
    }

    public Order getOrderByUUID(UUID uuid) {
        Order order = this.orderRepository.getAll().stream()
                .filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        return order;
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


}
