package model.repositories;

import controller.exceptions.RepozytoryException;
import lombok.NoArgsConstructor;
import model.entities.Good;

import java.util.List;
@NoArgsConstructor
public class GoodRepository implements Repository<Good> {
    private List<Good> goods;

    @Override
    public void add(Good element) throws RepozytoryException {
        for (Good good:goods) {
            if(good.equals(element))
                throw new RepozytoryException("This good already exists");
        }
        this.goods.add(element);
    }

    @Override
    public void remove(Good element) throws RepozytoryException {
        for (Good good:goods) {
            if(good.equals(element)) {
                this.goods.remove(element);
            }
        }
        throw new RepozytoryException("This good doesn't exist");
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
