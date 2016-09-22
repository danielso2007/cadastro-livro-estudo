package br.com.estudo.cadastrolivros.impl.services;

import br.com.estudo.cadastrolivros.interfaces.dao.BookDAO;
import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.modal.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl extends GenericServiceImpl<Book, BookDAO> implements BookService {

    @Autowired
    public BookServiceImpl(BookDAO dao) {
        super(dao);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Book> search(String description) {
        return getDao().searchBook(description);
    }


}
