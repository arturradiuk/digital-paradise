package model.repositories;

import controller.exceptions.RepositoryException;

import java.util.List;

public interface Repository<T> {
    void add(T element) throws RepositoryException;
    void remove(T element) throws RepositoryException;
    List<T> getAll();
    String toString();
}
