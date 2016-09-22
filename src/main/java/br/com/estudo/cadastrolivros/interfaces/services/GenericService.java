package br.com.estudo.cadastrolivros.interfaces.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface GenericService<T> {

    T getById(Long id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> listAll();
}
