package fillers;

import controller.exceptions.GoodException;
import model.entities.Good;
import model.goods.Laptop;

import java.util.ArrayList;
import java.util.List;

public class StaticGoodFiller implements DataFiller {
//    @Override
//    public void Fill(List<Good> goodList) {
//        goodList = new ArrayList<>();
//        Good good = new Laptop();
//        goodList.add(good);
//        System.out.println(Arrays.toString(goodList.toArray()));
//    }

    @Override
    public List<Good> Fill() {
        List<Good> goods = new ArrayList<>();
        try {
            goods.add(new Laptop(110, 4, "Lenovo", 16, 256, 13, true, 2));
            goods.add(new Laptop(120, 4, "Apple", 16, 256, 13, true, 2));
            goods.add(new Laptop(130, 4, "HP", 16, 256, 13, true, 2));
            goods.add(new Laptop(140, 4, "Dell", 16, 256, 13, true, 2));
            goods.add(new Laptop(150, 4, "Acer", 16, 256, 13, true, 2));

        } catch (GoodException e) {
            e.printStackTrace();
        }
        return goods;
    }
}
