package controller.managers;

import controller.exceptions.manager.GoodManagerException;
import controller.exceptions.repository.RepositoryException;
import lombok.NoArgsConstructor;
import model.entities.Good;
import model.entities.Order;
import model.goods.Laptop;
import model.goods.PC;
import model.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static controller.exceptions.manager.GoodManagerException.CANNON_DELETE;

@Named
@ApplicationScoped
@NoArgsConstructor
public class GoodManager implements IManager<Good, UUID> {

    @Inject
    private Repository<Good, UUID> goodRepository;


    public Good getGoodByUUID(UUID uuid) throws RepositoryException {
        return this.goodRepository.getResourceByUUID(uuid);
    }


    @Override
    public void update(UUID uuid, Good good) throws RepositoryException {
        this.goodRepository.update(uuid, good);
    }

    @Override
    public void add(Good good) throws RepositoryException {
        this.goodRepository.add(good);
    }

    @Override
    public void remove(Good good) throws RepositoryException {
        this.goodRepository.remove(good);
    }

    public void remove(OrderManager orderManager, Good good) throws GoodManagerException, RepositoryException {
        for (Order o : orderManager.getAll()) {
            for (Good g : o.getGoods()) {
                if (g.getUuid().equals(good.getUuid())) {
                    throw new GoodManagerException(CANNON_DELETE);
                }
            }
        }
        this.goodRepository.remove(good);
    }

    @Override
    public List<Good> getAll() {
        return this.goodRepository.getAll();
    }


    public List<Good> getAllCurrentLaptops() {
        return this.getAll().stream().filter(laptop -> laptop instanceof Laptop).collect(Collectors.toList());
    }

    public List<Good> getAllCurrentPCs() {
        return this.getAll().stream().filter(pc -> pc instanceof PC).collect(Collectors.toList());
    }

}

