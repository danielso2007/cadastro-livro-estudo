package br.com.estudo.cadastrolivros.impl.services;

import br.com.estudo.cadastrolivros.interfaces.services.GenericService;
import br.com.estudo.cadastrolivros.model.domain.BaseEntity;
import br.com.estudo.cadastrolivros.model.repositories.QueryDslCustomerRepository;
import com.querydsl.core.types.Predicate;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class GenericServiceImpl<T, E extends BaseEntity, ID extends Serializable, R extends QueryDslCustomerRepository> implements GenericService<T, E, ID, R> {

    @Autowired(required = false)
    private Logger logger;

    @Autowired
    private Mapper mapper;

    private R repository;

    private Class<E> entityClass;
    private Class<T> transferObjectClass;

    public GenericServiceImpl(R repository) {
        this.repository = repository;
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            this.transferObjectClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
    }

    @Override
    public Class<E> getEntityClass() {
        return null;
    }

    @Override
    public Class<T> getTansferObjectClass() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(ID id) {
        return mapper.map(this.repository.findOne(id), this.transferObjectClass);
    }

    @Override
    @Transactional
    public T save(T to) {
        E entity = mapper.map(to, entityClass);
        return mapper.map(this.repository.save(entity), transferObjectClass);
    }

    @Override
    @Transactional
    public T update(T to) {
        //FIXME: Refatorar e garantir que os dados estão vindos corretamente.
        E entity = mapper.map(to, entityClass);
        return mapper.map(this.repository.save(entity), transferObjectClass);
    }

    @Override
    @Transactional
    public void delete(T to) {
        this.repository.delete(this.repository.findOne(mapper.map(to, entityClass)));
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(this.repository.findOne(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> listAll() {
        return converterList(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> search(Predicate predicate) {
        return converterList(this.repository.findAll(predicate));
    }

    private List<T> converterList(Iterable<E> iterable) {
        return converterList(iterable.iterator());
    }

    private List<T> converterList(Iterator<E> iterator) {
        List<T> result = new ArrayList<T>();
        while (iterator.hasNext()) {
            // Mapeando para um Transfer Object.
            result.add(mapper.map(iterator.next(), transferObjectClass));
        }
        return result;
    }

    @Override
    public R getRepository() {
        return this.repository;
    }
}
