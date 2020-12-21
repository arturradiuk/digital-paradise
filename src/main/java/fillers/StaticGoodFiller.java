package fillers;

import controller.exceptions.GoodException;
import model.entities.Good;
import model.goods.Laptop;
import model.goods.PC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

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
        List<Good> goods = new CopyOnWriteArrayList<>();
//        List<Good> goods = new ArrayList<>(); // todo causes java.util.ConcurrentModificationException during remove operation; is there any other solution
        try {
            Good temp = new Laptop(110, "Lenovo", 16, 256, 13, true, 2, 5);
            temp.setUuid(UUID.fromString("14a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

            temp = new Laptop(120, "Apple", 16, 256, 13, true, 2, 6);
            temp.setUuid(UUID.fromString("24a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

            temp = new Laptop(130, "HP", 16, 256, 13, true, 2, 7);
            temp.setUuid(UUID.fromString("34a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

            temp = new Laptop(140, "Dell", 16, 256, 13, true, 2, 8);
            temp.setUuid(UUID.fromString("44a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

            temp = new Laptop(150, "Acer", 16, 256, 13, true, 2, 9);
            temp.setUuid(UUID.fromString("54a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

            temp = new PC(150, "Acer pc", 16, 256, 3);
            temp.setUuid(UUID.fromString("64a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

            temp = new PC(150, "Dell pc", 16, 256, 4);
            temp.setUuid(UUID.fromString("74a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

            temp = new PC(150, "HP pc", 16, 256, 6);
            temp.setUuid(UUID.fromString("84a6b7cc-bd4c-3022-83d0-d2af506bfb2b"));
            goods.add(temp);

        } catch (GoodException e) {
            e.printStackTrace();
        }
        return goods;
    }
}
