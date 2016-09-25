package br.com.estudo.cadastrolivros.interfaces.services;

import br.com.estudo.cadastrolivros.model.domain.BaseEntity;
import br.com.estudo.cadastrolivros.model.repositories.QueryDslCustomerRepository;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;

public interface GenericService<E extends BaseEntity, ID extends Serializable, R extends QueryDslCustomerRepository> {

    Class<E> getObjectClass();

    E getById(ID id);

    E save(E entity);

    E update(E entity);

    void delete(E entity);

    List<E> listAll();

    List<E> search(Predicate predicate);

    R getRepository();
}
