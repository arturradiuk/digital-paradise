package web;

import controller.exceptions.repository.RepositoryException;
import controller.managers.GoodManager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.entities.Good;
import model.goods.Laptop;
import model.goods.PC;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


@Data
@Named
@SessionScoped
public class GoodController implements Serializable {
    @Inject
    private GoodManager goodManager;

    @Getter
    @Setter
    private String uuid;

    private Good newLaptop = new Laptop();
    private Good newPC = new PC();

    private Good currentLaptop;
    private Good currentPC;

    private List<Good> currentLaptops;
    private List<Good> currentPCs;

    public String seeLaptop(Laptop laptop) {
        this.currentLaptop = laptop;
        return "UpdateLaptop";
    }

    public String updateLaptop() {
        try {

            this.goodManager.update(this.currentLaptop.getUuid(), this.currentLaptop);

        } catch (RepositoryException e) {
            e.printStackTrace();
            this.initCurrentGoods();
            return "UpdateProductFailure";
        }

        return "AllGoods";
    }

    public String processNewLaptop() {

        if (null == newLaptop.getGoodName() || newLaptop.getGoodName().isEmpty()) { //todo
            throw new IllegalArgumentException("Proba zatwirdzenia NewLaptop bez goodName danych.");
        }
        try {
            this.goodManager.add(this.newLaptop);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return "AddProductFailure";

        }

        this.newLaptop = new Laptop();

        this.initCurrentGoods();
        return "AllGoods";
    }

    public String seePC(PC pc) {
        this.currentPC = pc;
        return "UpdatePC";
    }

    public String updatePC() {
        try {
            this.goodManager.update(this.currentPC.getUuid(), this.currentPC);

        } catch (RepositoryException e) {
            e.printStackTrace();
            return "UpdateProductFailure";
        }
        this.initCurrentGoods();
        return "AllGoods";
    }

    public String processNewPC() {

        if (null == newPC.getGoodName() || newPC.getGoodName().isEmpty()) { //todo
            throw new IllegalArgumentException("Proba zatwirdzenia NewPC bez goodName danych.");
        }
        try {
            this.goodManager.add(this.newPC);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return "AddProductFailure";
        }

        this.newPC = new PC();

        this.initCurrentGoods();
        return "AllGoods";
    }


    public String removeGood(Good good) {
        try {
            this.goodManager.remove(good);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return "ProductRemoveFailure";
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

    public List<Good> getCurrentLaptops() {
        return this.currentLaptops;
    }

    public List<Good> getCurrentPCs() {
        return this.currentPCs;
    }

    public String findGoodById() {
        this.initCurrentGoodsById();
        return "FindById";
    }

    private void initCurrentGoodsById() {
        Good g = this.goodManager.getGoodByUUID(UUID.fromString(this.uuid));

        if (g instanceof Laptop) {
            this.currentLaptops = new CopyOnWriteArrayList<>();
            this.currentLaptops.add(g);
            return;
        }

        if (g instanceof PC) {
            this.currentPCs = new CopyOnWriteArrayList<>();
            this.currentPCs.add(g);
            return;
        }


    }


}
