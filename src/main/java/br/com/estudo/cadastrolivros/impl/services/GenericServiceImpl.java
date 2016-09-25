package br.com.estudo.cadastrolivros.impl.services;

import br.com.estudo.cadastrolivros.interfaces.services.GenericService;
import br.com.estudo.cadastrolivros.model.domain.BaseEntity;
import br.com.estudo.cadastrolivros.model.repositories.QueryDslCustomerRepository;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class GenericServiceImpl<E extends BaseEntity, ID extends Serializable, R extends QueryDslCustomerRepository> implements GenericService<E, ID, R> {

    @Autowired(required = false)
    private Logger logger;

    private R repository;

    private Class<E> persistentClass;

    public GenericServiceImpl(R repository) {
        this.repository = repository;
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            this.persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            persistentClass = (Class<E>) getClass().getGenericSuperclass();
        }
    }

    @Override
    public Class<E> getObjectClass() {
        return this.persistentClass;
    }

    @Override
    @Transactional(readOnly = true)
    public E getById(ID id) {
        return (E) this.repository.findOne(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return (E) this.repository.save(entity);
    }

    @Override
    @Transactional
    public E update(E entity) {
        return (E) this.repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(E entity) {
        this.repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> listAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> search(Predicate predicate) {
        return Lists.newArrayList(this.repository.findAll(predicate));
    }

    @Override
    public R getRepository() {
        return this.repository;
    }
}
