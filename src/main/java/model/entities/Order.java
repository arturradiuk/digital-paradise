package model.entities;

import controller.exceptions.OrderException;
import lombok.Getter;
import lombok.ToString;
import model.clients.Client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ToString
public class Order {
    @Getter
    private UUID uuid = UUID.randomUUID();
    @Getter
    private LocalDateTime orderDateTime;
    @Getter
    private List<Good> goods = new ArrayList<Good>();
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

    public void addGood(Good good) throws OrderException {
        if (good == null) {
            throw new OrderException("good cannot be null");
        }
        this.goods.add(good);
    }

    public void removeGood(Good good) throws OrderException {
        if (!this.goods.contains(good)) {
            throw new OrderException("you cannot remove this good");
        }
        this.goods.remove(good);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(uuid, order.uuid) &&
                Objects.equals(orderDateTime, order.orderDateTime) &&
                Objects.equals(goods, order.goods) &&
                Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, orderDateTime, goods, client);
    }
}
