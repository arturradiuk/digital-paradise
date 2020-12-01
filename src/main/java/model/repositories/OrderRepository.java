package model.repositories;

import controller.exceptions.RepositoryException;
import fillers.DataFiller;
import fillers.StaticGoodFiller;
import fillers.StaticOrderFiller;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.Order;

import javax.annotation.PostConstruct;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRepository implements Repository<Order> {// todo write methods getBy...
    private List<Order> orders;

    @Override
    public String toString() {
        return "OrderRepository{" +
                "orders=" + orders +
                '}';
    }

    public OrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void add(Order element) throws RepositoryException {
        for (Order order : orders) {
            if (order.equals(element))
                throw new RepositoryException("This order already exists");
        }
        this.orders.add(element);
    }

    @Override
    public void remove(Order element) throws RepositoryException {
        for (Order order : orders) {
            if (order.equals(element)) {
                this.orders.remove(element);
            }
        }
        throw new RepositoryException("This order doesn't exist");
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @PostConstruct
    private void initOrders() {
        DataFiller dataFiller = new StaticOrderFiller();
        this.orders = dataFiller.Fill();
    }
}
