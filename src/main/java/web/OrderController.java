package web;

import controller.exceptions.OrderException;
import controller.managers.GoodManager;
import controller.managers.OrderManager;
import controller.managers.UserManager;
import lombok.Data;
import model.clients.Client;
import model.entities.Good;
import model.entities.Order;
import model.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@Named
@SessionScoped
public class OrderController implements Serializable {
    @Inject
    private OrderManager orderManager;
    @Inject
    private UserManager userManager;
    @Inject
    private GoodManager goodManager;


    private Order newOrder = new Order();
    private Order currentOrder = new Order();

    private UUID clientUuid;
    private List<UUID> goodsUuid;

    private List<Order> currentOrders;

    @PostConstruct
    public void initCurrentOrders() {
        this.currentOrders = orderManager.getAllOrders();
    }

    public String processNewOrder() {
//        System.out.println("hellohellohellohellohellohellohellohellohellohellohellohellohellohello");
        User user = this.userManager.getClientByUUID(clientUuid); // todo should be client
//        System.out.println(user);
        List<Good> goods = new ArrayList<>();

        for (int i = 0; i < this.goodsUuid.size(); i++) {
            goods.add(this.goodManager.getGoodByUUID(this.goodsUuid.get(i)));
        }
//        System.out.println(Arrays.toString(goods.toArray()));
        try {
            this.orderManager.addOrder(new Order(LocalDateTime.now(), goods, (Client) user));
        } catch (OrderException e) {
            e.printStackTrace();
        }
        this.newOrder = new Order();
        this.initCurrentOrders();
        return "AllOrders";
    }


}
