package br.com.estudo.cadastrolivros.impl.dao;

import br.com.estudo.cadastrolivros.interfaces.dao.GenericDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericHibernateDAO<T> implements GenericDAO<T> {

    private Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericHibernateDAO() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            persistentClass = (Class<T>) getClass().getGenericSuperclass();
        }
    }

    @Override
    public Class<T> getObjectClass() {
        return this.persistentClass;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T getById(Long id) {
        return getCurrentSession().find(persistentClass, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveOrUpdate(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(T entity) {
        getCurrentSession().delete(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> list() {
        Criteria criteria = getCurrentSession().createCriteria(persistentClass);
        return criteria.list();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}