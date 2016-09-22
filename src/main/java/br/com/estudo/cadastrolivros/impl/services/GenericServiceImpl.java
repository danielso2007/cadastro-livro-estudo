package br.com.estudo.cadastrolivros.impl.services;

import br.com.estudo.cadastrolivros.interfaces.dao.GenericDAO;
import br.com.estudo.cadastrolivros.interfaces.services.GenericService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenericServiceImpl<T, DAO extends GenericDAO<T>> implements GenericService<T> {
	
	private DAO dao;

    public GenericServiceImpl(DAO dao) {
        this.dao = dao;
    }

    public DAO getDao() {
        return dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(T entity) {
		dao.saveOrUpdate(entity);
		
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(T entity) {
		dao.saveOrUpdate(entity);
		
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
		
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> listAll() {
		List<T> list = dao.list();
		return list;
	}
	
}
