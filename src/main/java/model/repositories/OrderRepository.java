package model.repositories;

import controller.exceptions.repository.OrderRepositoryException;
import controller.exceptions.repository.RepositoryException;
import fillers.DataFiller;
import fillers.StaticOrderFiller;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.Order;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@NoArgsConstructor
public class OrderRepository implements Repository<Order, UUID> {// todo write methods getBy...
    private List<Order> orders;

    @Override
    public void update(UUID id, Order element) {
        synchronized (this.orders) {
            for (int i = 0; i < orders.size(); i++) {
                if (id.equals(orders.get(i).getUuid())) {
                    this.orders.set(i, element);
                }
            }
        }
    }


    public OrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void add(Order element) throws RepositoryException {
        synchronized (this.orders) {
            for (Order order : orders) {
                if (order.equals(element))
                    throw new OrderRepositoryException(OrderRepositoryException.EXIST_ORDER);
            }
            this.orders.add(element);
        }
    }

    @Override
    public void remove(Order element) throws RepositoryException {
        synchronized (this.orders) {
            if (!this.orders.remove(element)) {

                throw new OrderRepositoryException(OrderRepositoryException.NOT_EXIST_ORDER);
            }
        }
    }

    @Override
    public List<Order> getAll() {
        synchronized (this.orders) {
            return new CopyOnWriteArrayList<>(orders);
        }
    }

    @PostConstruct
    private void initOrders() {
        DataFiller dataFiller = new StaticOrderFiller();
        this.orders = dataFiller.Fill();
    }
}
