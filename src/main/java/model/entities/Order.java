package model.entities;

import controller.exceptions.OrderException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    @Getter
    private UUID uuid = UUID.randomUUID();
    @Getter
    private LocalDateTime orderDateTime;
    @Getter
    private List<Good> goods;
    @Getter
    private Client client;

    public Order() {
    }

    public Order(LocalDateTime orderDateTime, List<Good> goods, Client client) throws OrderException {
        if (orderDateTime == null || goods == null || client == null) {
            throw new OrderException("NullPointerException in model.entities.Order.Order()");
        }
        this.orderDateTime = orderDateTime;
        this.goods = goods;
        this.client = client;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) throws OrderException {
        if (orderDateTime == null) {
            throw new OrderException("orderDateTime cannot be null.");
        }
        this.orderDateTime = orderDateTime;
    }

    public void setGoods(List<Good> goods) throws OrderException {
        if (goods == null) {
            throw new OrderException("goods cannot be null.");
        }
        this.goods = goods;
    }

    public void setClient(Client client) throws OrderException {
        if (client == null) {
            throw new OrderException("client cannot be null.");
        }
        this.client = client;
    }
}
