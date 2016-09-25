package br.com.estudo.cadastrolivros.interfaces.services;

import java.io.Serializable;
import java.util.List;

import br.com.estudo.cadastrolivros.model.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GenericService<E extends BaseEntity, ID extends Serializable, R extends CrudRepository> {

    Class<E> getObjectClass();

    E getById(ID id);

    E save(E entity);

    E update(E entity);

    void delete(E entity);

    List<E> listAll();

    R getRepository();
}
