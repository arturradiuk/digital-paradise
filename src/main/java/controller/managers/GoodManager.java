package controller.managers;

import controller.exceptions.RepositoryException;
import lombok.NoArgsConstructor;
import model.entities.Good;
import model.goods.Laptop;
import model.goods.PC;
import model.repositories.Repository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
@NoArgsConstructor
public class GoodManager implements Serializable {
    private UUID managerUuid = UUID.randomUUID();

    @Inject
    private Repository<Good,UUID> goodRepository; // = new GoodRepository();


    public void updateGood(UUID uuid,Good good){
        try {
            this.goodRepository.update(uuid,good);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    public void addGood(Good good) {
        try {
            this.goodRepository.add(good);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }

    public void removeGood(Good good) { // save deleting
        try {
            this.goodRepository.remove(good);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    public Good getGoodByUUID(UUID uuid) {
        Good good = this.goodRepository.getAll().stream()
                .filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        return good;
    }

    public List<Good> getAllGoods() {
        return this.goodRepository.getAll();
    }

    public List<Good> getAllCurrentLaptops() {
        List<Good> laptops = this.getAllGoods().stream().filter(lapop -> lapop instanceof Laptop).collect(Collectors.toList());
        return laptops;
    }

    public List<Good> getAllCurrentPCs() {
        List<Good> pcs = this.getAllGoods().stream().filter(pc -> pc instanceof PC).collect(Collectors.toList());
        return pcs;
    }

}

