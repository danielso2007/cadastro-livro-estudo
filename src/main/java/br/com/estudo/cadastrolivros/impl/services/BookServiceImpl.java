package br.com.estudo.cadastrolivros.impl.services;

import java.util.List;

import br.com.estudo.cadastrolivros.interfaces.dao.BookDAO;
import br.com.estudo.cadastrolivros.modal.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.interfaces.services.GenericServiceImpl;

@Service
public class BookServiceImpl extends GenericServiceImpl<Book, BookDAO> implements BookService {

	@Autowired
	@Qualifier("bookDAOImpl")
	@Override
	public void setDao(BookDAO dao) {
		super.setDao(dao);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Book getById(int id) {
		
		return getDao().getById(id);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Book> search(String description) {
			
		return getDao().searchBook(description);
	}

	
}
