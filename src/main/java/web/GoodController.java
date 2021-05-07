package web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.exceptions.manager.GoodManagerException;
import controller.exceptions.repository.RepositoryException;
import controller.managers.GoodManager;
import controller.managers.OrderManager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.entities.Good;
import model.goods.Laptop;
import model.goods.PC;



@Data
@Named
@SessionScoped
public class GoodController implements Serializable {

    @Inject
    private GoodManager goodManager;
    @Inject
    private OrderManager orderManager;

    @Getter
    @Setter
    private String uuid;

    private Good newGood;

    @Getter
    @Setter
    private String errorMessage = new String();

    private Good currentLaptop;
    private Good currentPC;

    private Good currentGood;

    private List<Good> currentLaptops;
    private List<Good> currentPCs;

    public String seeLaptop(Laptop laptop) {
        this.currentGood = laptop;
        return "UpdateGood";
    }

    public String updateLaptop() {
        try {
            this.goodManager.update(this.currentGood.getUuid(), this.currentGood);

        } catch (RepositoryException e) {
            e.printStackTrace();
            this.initCurrentGoods();
            this.errorMessage = e.getMessage();

            return "GoodError";
        }

        return "AllGoods";
    }

    public String processNewLaptop() {
        if (null == newGood) {
            this.addLaptop();
        } else if (null == newGood.getGoodName() || newGood.getGoodName().isEmpty() || newGood.getCount() < 0) {
            throw new IllegalArgumentException("Number of goods can't be zero and good name can't be empty");
        }
        try {
            this.goodManager.add(this.newGood);
        } catch (RepositoryException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();
            return "GoodError";

        }

        this.initCurrentGoods();
        return "AllGoods";
    }

    public String seePC(PC pc) {
        this.currentGood = pc;
        return "UpdateGood";
    }

    public String updatePC() {
        try {
            this.goodManager.update(this.currentGood.getUuid(), this.currentGood);

        } catch (RepositoryException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();
            return "GoodError";
        }
        this.initCurrentGoods();
        return "AllGoods";
    }

    public String processNewPC() {
        if (null == newGood) {
            this.addPC();
        } else if (null == newGood.getGoodName() || newGood.getGoodName().isEmpty() || newGood.getCount() < 0) {
            throw new IllegalArgumentException("Number of goods can't be zero and good name can't be empty");
        }
        try {
            this.goodManager.add(this.newGood);
        } catch (RepositoryException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();

            return "GoodError";
        }
        this.initCurrentGoods();
        return "AllGoods";
    }


    public String removeGood(Good good) {
        try {
            this.goodManager.remove(this.orderManager, good);
        } catch (RepositoryException | GoodManagerException e) {
            e.printStackTrace();
            this.errorMessage = e.getMessage();

            return "GoodError";
        }
        this.initCurrentGoods();
        return "AllGoods";
    }

    @PostConstruct
    public void initCurrentGoods() {
        this.currentLaptops = this.goodManager.getAllCurrentLaptops();
        this.currentPCs = this.goodManager.getAllCurrentPCs();
    }

    public String refresh() {
        this.initCurrentGoods();
        return "AllGoods";
    }

    public String addLaptop() {
        this.newGood = new Laptop();
        return "AddGood";
    }

    public String addPC() {
        this.newGood = new PC();
        return "AddGood";
    }

    public boolean isNewGoodLaptop() {
        if (this.newGood == null) {
            return false;
        }
        return this.newGood.getClass().equals(Laptop.class);
    }

    public boolean isNewGoodPC() {
        if (this.newGood == null) {
            return false;
        }
        return this.newGood.getClass().equals(PC.class);
    }

    public boolean isCurrentGoodLaptop() {
        if (this.currentGood == null) {
            return false;
        }
        return this.currentGood.getClass().equals(Laptop.class);
    }

    public boolean isCurrentGoodPC() {
        if (this.currentGood == null) {
            return false;
        }
        return this.currentGood.getClass().equals(PC.class);
    }

    public List<Good> getCurrentLaptops() {
        return this.currentLaptops;
    }

    public List<Good> getCurrentPCs() {
        return this.currentPCs;
    }

    public String findGoodById() {
        Good g = null;
        try {
            g = this.goodManager.getGoodByUUID(UUID.fromString(this.uuid));
        } catch (IllegalArgumentException | RepositoryException e) {
            this.errorMessage = e.getMessage();
            return "GoodError";

        }

        if (g instanceof Laptop) {
            this.currentLaptops = new CopyOnWriteArrayList<>();
            this.currentLaptops.add(g);
        }

        if (g instanceof PC) {
            this.currentPCs = new CopyOnWriteArrayList<>();
            this.currentPCs.add(g);
        }

        return "FindById";
    }


}
