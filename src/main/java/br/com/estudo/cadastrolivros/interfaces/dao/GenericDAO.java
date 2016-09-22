package br.com.estudo.cadastrolivros.interfaces.dao;

import java.util.List;

public interface GenericDAO<T> {

    T getById(Long id);

    void saveOrUpdate(T entity);

    void delete(T entity);

    List<T> list();

    Class<T> getObjectClass();

}
