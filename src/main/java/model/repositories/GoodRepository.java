package model.repositories;

import controller.exceptions.RepositoryException;
import lombok.NoArgsConstructor;
import model.entities.Good;

import java.util.List;
@NoArgsConstructor
public class GoodRepository implements Repository<Good> {
    private List<Good> goods;

    @Override
    public void add(Good element) throws RepositoryException {
        for (Good good:goods) {
            if(good.equals(element))
                throw new RepositoryException("This good already exists");
        }
        this.goods.add(element);
    }

    @Override
    public void remove(Good element) throws RepositoryException {
        for (Good good:goods) {
            if(good.equals(element)) {
                this.goods.remove(element);
            }
        }
        throw new RepositoryException("This good doesn't exist");
    }

    @Override
    public List<Good> getAll() {
        return goods;
    }

    @Override
    public String toString() {
        return "GoodRepository{" +
                "goods=" + goods +
                '}';
    }
}
