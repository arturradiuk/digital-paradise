package model.repositories;

import controller.exceptions.RepositoryException;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.Good;
import fillers.DataFiller;
import fillers.StaticGoodFiller;
import model.entities.User;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


//@ApplicationScoped // todo check the necessity
@Data
@NoArgsConstructor
public class GoodRepository implements Repository<Good, UUID> { // todo write methods getBy...
    private List<Good> goods;

    @Override
    public void add(Good element) throws RepositoryException {
        synchronized (this.goods) {
            if (this.goods.contains(element)) {

                throw new RepositoryException("This good already exists");
            }

            this.goods.add(element);
        }
    }

    @Override
    public void remove(Good element) throws RepositoryException {
        synchronized (this.goods) {
            if (!this.goods.remove(element)) {

                throw new RepositoryException("This good doesn't exist");
            }
        }
    }

    @Override
    public List<Good> getAll() {
        synchronized (this.goods) {
            return new CopyOnWriteArrayList<>(goods);
        }
    }

    @Override
    public void update(UUID id, Good element) throws RepositoryException {
        synchronized (this.goods) {
            for (int i = 0; i < goods.size(); i++) {
                if (id.equals(goods.get(i).getUuid())) {
                    this.goods.set(i, element);
                }
            }
        }
    }

    @PostConstruct
//    private void initGoods() {// todo because of using in static filler
    public void initGoods() {
        DataFiller dataFiller = new StaticGoodFiller();
        this.goods = dataFiller.Fill();
    }

}
