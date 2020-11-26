package model.repositories;

import java.util.List;

public interface Repository<T> {
    void add(T element);
    void remove(T element);
    List<T> getAll();
    String toString();
}
