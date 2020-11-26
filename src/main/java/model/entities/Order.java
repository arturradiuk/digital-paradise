package model.entities;

import controller.exceptions.ClientException;
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

    public Order(LocalDateTime orderDateTime, List<Good> goods, Client client) throws ClientException {
        if(orderDateTime == null || goods == null || client == null){
            throw new ClientException("");
        }
        this.orderDateTime = orderDateTime;
        this.goods = goods;
        this.client = client;
    }



}
