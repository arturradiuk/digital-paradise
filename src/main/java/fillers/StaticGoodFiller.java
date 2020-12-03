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
            Good temp = new Laptop(110, "Lenovo", 16, 256, 13, true, 2);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("1234567890_good").getBytes()));
            goods.add(temp);

            temp = new Laptop(120, "Apple", 16, 256, 13, true, 2);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("2234567890_good").getBytes()));
            goods.add(temp);

            temp = new Laptop(130, "HP", 16, 256, 13, true, 2);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("3234567890_good").getBytes()));
            goods.add(temp);

            temp = new Laptop(140, "Dell", 16, 256, 13, true, 2);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("4234567890_good").getBytes()));
            goods.add(temp);

            temp = new Laptop(150, "Acer", 16, 256, 13, true, 2);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("5234567890_good").getBytes()));
            goods.add(temp);

            temp = new PC(150, "Acer pc", 16, 256);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("6234567890_good").getBytes()));
            goods.add(temp);

            temp = new PC(150, "Dell pc", 16, 256);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("7234567890_good").getBytes()));
            goods.add(temp);

            temp = new PC(150, "HP pc", 16, 256);
            temp.setUuid(UUID.nameUUIDFromBytes(new String("8234567890_good").getBytes()));
            goods.add(temp);

        } catch (GoodException e) {
            e.printStackTrace();
        }
        return goods;
    }
}
