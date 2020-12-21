package web;

import controller.exceptions.OrderException;
import controller.exceptions.repository.RepositoryException;
import controller.managers.GoodManager;
import controller.managers.OrderManager;
import controller.managers.UserManager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.clients.Client;
import model.entities.Good;
import model.entities.Order;
import model.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

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

    @Getter
    @Setter
    private String orderUuid = new String();

    @Getter
    @Setter
    private String userUuid = new String();


    private Order newOrder = new Order();
    private Order currentOrder = new Order();

    private UUID clientUuid;
    private List<UUID> goodsUuid;

    private List<Order> currentOrders;


    public String processNewOrder() {
//        System.out.println("hellohellohellohellohellohellohellohellohellohellohellohellohellohello");
        User user = this.userManager.getUserByUUID(clientUuid); // todo should be client
//        System.out.println(user);
        List<Good> goods = new ArrayList<>();

        for (int i = 0; i < this.goodsUuid.size(); i++) {
            goods.add(this.goodManager.getGoodByUUID(this.goodsUuid.get(i)));
//            this.goodManager.removeGood((this.goodManager.getGoodByUUID(this.goodsUuid.get(i))));
        }

//            this.orderManager.addOrder(new Order(LocalDateTime.now(), goods, (Client) user));
        try {
            this.orderManager.createOrder(this.goodManager, goods, (Client) user);
        } catch (OrderException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page
        }


        this.newOrder = new Order();
        this.clientUuid = null;
        this.goodsUuid = null;
        this.initCurrentOrders();
        return "AllOrders";
    }

    public String returnOrder(Order order) {
//        this.orderManager.returnOrder(order);
        try {
            this.orderManager.returnOrder(this.goodManager, order);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ""; // todo redirect to the error page
        }
        this.initCurrentOrders();
//        for (Good g : order.getGoods()) {
//            this.goodManager.addGood(g);
//        }
        return "AllOrders";
    }


    public String removeOrder(Order order) throws RepositoryException {
        this.orderManager.remove(order);
        this.initCurrentOrders();
        return "AllOrders";
    }


    public String refresh() {
        System.out.println("in order refresh");
        this.initCurrentOrders();
        return "AllOrders";
    }

    @PostConstruct
    public void initCurrentOrders() {
        this.currentOrders = orderManager.getAll();
    }

    // todo implement update

    public String findOrderById() {
        this.initCurrentOrdersById();
        return "FindById";
    }


    private void initCurrentOrdersById() {
        Order o = this.orderManager.getOrderByUUID(UUID.fromString(this.orderUuid));
        this.currentOrders = new CopyOnWriteArrayList<>();
        this.currentOrders.add(o);
    }

    public String findOrdersForUserById() {
        this.initCurrentOrdersByUserId();
        return "FindById";
    }

    private void initCurrentOrdersByUserId() {
        User user = this.userManager.getUserByUUID(UUID.fromString(userUuid));
        System.out.println(user.toString());
        this.currentOrders = this.orderManager.getAllOrdersForTheUser(user);
    }


}
