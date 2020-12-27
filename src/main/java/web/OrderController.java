package web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.exceptions.ManagerException;
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


    @Getter
    @Setter
    private String errorMessage = new String();


    private Order newOrder = new Order();
    private Order currentOrder = new Order();

    private UUID clientUuid;
    private List<UUID> goodsUuid;

    private List<Order> currentOrders;

    @Inject
    IdentityUtils identityUtils;

    public List<Order> currentOrders() {
        String id = identityUtils.getMyLogin();
        List<Order> list = new ArrayList<>();

        boolean isClient = false;

        for (Order o: currentOrders) {
            if(o.getClient().getUuid().toString().equals(id)) {
                list.add(o);
                isClient = true;
            }


        }

        if(!isClient){
            list = currentOrders;
        } else {
            currentOrders = list;
        }
        

        return list;
    }


    public String processNewOrder() {
        User user = null; // todo should be client
        try {
        user = this.userManager.getUserByUUID(UUID.fromString(identityUtils.getMyLogin()));
        } catch (RepositoryException | ClassCastException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();
            return "OrderError";
        }
        List<Good> goods = new ArrayList<>();

        for (int i = 0; i < this.goodsUuid.size(); i++) {
            try {
                goods.add(this.goodManager.getGoodByUUID(this.goodsUuid.get(i)));
            } catch (IllegalArgumentException | RepositoryException e) {
                this.errorMessage = e.getMessage();
                return "OrderError";
            }
        }

        try {
            if (user instanceof Client) {
                this.orderManager.createOrder(this.goodManager, goods, (Client) user);
            }else {
                this.errorMessage = "This user is not Client";
                return "OrderError";
            }
        } catch (OrderException | ManagerException | RepositoryException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();
            return "OrderError";
        }


        this.newOrder = new Order();
        this.clientUuid = null;
        this.goodsUuid = null;
        this.initCurrentOrders();
        return "AllOrders";
    }

    public String returnOrder(Order order) {
        try {
            this.orderManager.returnOrder(this.goodManager, order);
        } catch (RepositoryException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();

            return "OrderError";
        }
        this.initCurrentOrders();

        return "AllOrders";
    }


    public String removeOrder(Order order) {
        try {
            this.orderManager.remove(order);
        } catch (RepositoryException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();

            return "OrderError";
        }
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

    public String findOrderById() {

        try {
            this.initCurrentOrdersById();
        } catch (RepositoryException | IllegalArgumentException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();
            return "OrderError";
        }

        return "FindById";
    }


    private void initCurrentOrdersById() throws RepositoryException {

        Order o = null;
        o = this.orderManager.getOrderByUUID(UUID.fromString(this.orderUuid));
        this.currentOrders = new CopyOnWriteArrayList<>();
        this.currentOrders.add(o);
    }

    public String findOrdersForUserById() {
        try {
            this.initCurrentOrdersByUserId();
        } catch (RepositoryException | IllegalArgumentException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();
            return "OrderError";
        }
        return "FindById";
    }

    private void initCurrentOrdersByUserId() throws RepositoryException {
        User user = this.userManager.getUserByUUID(UUID.fromString(userUuid));

        System.out.println(user.toString());
        this.currentOrders = this.orderManager.getAllOrdersForTheUser(user);
    }
}
