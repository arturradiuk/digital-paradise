package model.repositories;

import controller.exceptions.RepozytoryException;
import model.entities.Order;

import java.util.List;

public class OrderRepository implements Repository<Order>{
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
    public void add(Order element) throws RepozytoryException {
        for (Order order:orders) {
            if(order.equals(element))
                throw new RepozytoryException("This order already exists");
        }
        this.orders.add(element);
    }

    @Override
    public void remove(Order element) throws RepozytoryException {
        for (Order order:orders) {
            if(order.equals(element)) {
                this.orders.remove(element);
            }
        }
        throw new RepozytoryException("This order doesn't exist");
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }
}
