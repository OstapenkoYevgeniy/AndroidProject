package com.gmail.ostapenkoyevgeniy.skynet.dao;

import java.util.List;

public interface Dao<T, K> {
    boolean insert(T object) throws DaoException;

    boolean update(T object) throws DaoException;

    boolean deleteById(K id) throws DaoException;

    T getById(K key) throws DaoException;

    T getByColumn(String column, String value) throws DaoException;

    List<T> getAll() throws DaoException;

}
