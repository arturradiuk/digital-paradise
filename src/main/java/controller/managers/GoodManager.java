package controller.managers;

import controller.exceptions.repository.RepositoryException;
import lombok.NoArgsConstructor;
import model.entities.Good;
import model.goods.Laptop;
import model.goods.PC;
import model.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
@NoArgsConstructor
public class GoodManager implements IManager<Good, UUID> {

    @Inject
    private Repository<Good, UUID> goodRepository; // = new GoodRepository();


    @Override
    public void update(UUID uuid, Good good) throws RepositoryException {
        this.goodRepository.update(uuid, good);
    }

    @Override
    public void add(Good good) throws RepositoryException {
        this.goodRepository.add(good);
    }

    @Override
    public void remove(Good good) throws RepositoryException { // save deleting
        this.goodRepository.remove(good);
    }

    @Override
    public List<Good> getAll() {
        return this.goodRepository.getAll();
    }

    public Good getGoodByUUID(UUID uuid) {

//        Good good = this.goodRepository.getAll().stream().filter(c -> c.getUuid().equals(uuid)).findFirst().orElse(null);
        Good good = null;
        for (Good g : this.goodRepository.getAll()) {
            if (g.getUuid().equals(uuid)) {
                good = g;
            }
        }
        return good;
    }

    public List<Good> getAllCurrentLaptops() {
        List<Good> laptops = this.getAll().stream().filter(lapop -> lapop instanceof Laptop).collect(Collectors.toList());
        return laptops;
    }

    public List<Good> getAllCurrentPCs() {
        List<Good> pcs = this.getAll().stream().filter(pc -> pc instanceof PC).collect(Collectors.toList());
        return pcs;
    }

}

