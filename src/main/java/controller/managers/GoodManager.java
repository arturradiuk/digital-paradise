package controller.managers;

import controller.exceptions.RepositoryException;
import lombok.NoArgsConstructor;
import model.entities.Good;
import model.repositories.GoodRepository;
import model.repositories.Repository;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class GoodManager {
    private Repository<Good> goodRepository = new GoodRepository();

    public void addGood(Good good) {
        try {
            this.goodRepository.add(good);
        } catch (RepositoryException e) { // todo handle exception
            e.printStackTrace();
        }
    }

    public void removeGood(Good good) {
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

}
