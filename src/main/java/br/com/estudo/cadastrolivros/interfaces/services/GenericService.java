package br.com.estudo.cadastrolivros.interfaces.services;

import br.com.estudo.cadastrolivros.model.domain.BaseEntity;
import br.com.estudo.cadastrolivros.model.repositories.QueryDslCustomerRepository;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, E extends BaseEntity, ID extends Serializable, R extends QueryDslCustomerRepository> {

    Class<E> getEntityClass();
    Class<T> getTansferObjectClass();

    T getById(ID id);

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    List<T> listAll();

    List<T> search(Predicate predicate);

    R getRepository();
}
