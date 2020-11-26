package controller.managers;


import lombok.Data;
import model.repositories.OrderRepository;

@Data
public class OrderManager {
    private OrderRepository orderRepository;

    public OrderManager() {
        this.orderRepository = new OrderRepository();
    }

    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
