package br.com.estudo.cadastrolivros.impl.dao;

import java.util.List;

import br.com.estudo.cadastrolivros.interfaces.dao.GenericDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericHibernateDAO<T> implements GenericDAO<T> {


	private Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;

	public GenericHibernateDAO(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}
	
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}
	
	public void delete(T entity) {
		getCurrentSession().delete(entity);
		
	}
	
	public List<T> list() {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		return criteria.list();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	};
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

}