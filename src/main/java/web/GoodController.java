package web;

import controller.managers.GoodManager;
import lombok.Data;
import model.entities.Good;
import model.goods.Laptop;
import model.goods.PC;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Data
@Named
@SessionScoped
public class GoodController implements Serializable {
    @Inject
    private GoodManager goodManager;

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
        this.goodManager.updateGood(this.currentLaptop.getUuid(), this.currentLaptop);
        this.initCurrentGoods();
        return "AllGoods";
    }

    public String processNewLaptop() {

        if (null == newLaptop.getGoodName() || newLaptop.getGoodName().isEmpty()) { //todo
            throw new IllegalArgumentException("Proba zatwirdzenia NewLaptop bez goodName danych.");
        }
        this.goodManager.addGood(this.newLaptop);

        this.newLaptop = new Laptop();

        this.initCurrentGoods();
        return "AllGoods";
    }

    public String seePC(PC pc) {
        this.currentPC = pc;
        return "UpdatePC";
    }

    public String updatePC() {
        this.goodManager.updateGood(this.currentPC.getUuid(), this.currentLaptop);
        this.initCurrentGoods();
        return "AllGoods";
    }

    public String processNewPC() {

        if (null == newPC.getGoodName() || newPC.getGoodName().isEmpty()) { //todo
            throw new IllegalArgumentException("Proba zatwirdzenia NewPC bez goodName danych.");
        }
        this.goodManager.addGood(this.newPC);

        this.newPC = new PC();

        this.initCurrentGoods();
        return "AllGoods";
    }


    public String removeGood(Good good) {
        this.goodManager.removeGood(good);
        this.initCurrentGoods();
        return "AllGoods";
    }

    @PostConstruct
    public void initCurrentGoods() {
        this.currentLaptops = this.goodManager.getAllCurrentLaptops();
        this.currentPCs = this.goodManager.getAllCurrentPCs();
    }

    public String refresh(){
        System.out.println("in good refresh");
        this.initCurrentGoods();
        return "AllGoods";
    }

    public List<Good> getCurrentLaptops() {
        return this.currentLaptops;
    }

    public List<Good> getCurrentPCs() {
        return this.currentPCs;
    }

}
