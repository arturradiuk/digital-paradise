package model.repositories;

import controller.exceptions.RepozytoryException;

import java.util.List;

public interface Repository<T> {
    void add(T element) throws RepozytoryException;
    void remove(T element) throws RepozytoryException;
    List<T> getAll();
    String toString();
}
