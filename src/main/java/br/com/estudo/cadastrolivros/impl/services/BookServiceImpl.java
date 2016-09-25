package br.com.estudo.cadastrolivros.impl.services;

import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.model.domain.Book;
import br.com.estudo.cadastrolivros.model.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl extends GenericServiceImpl<Book, Long, BookRepository> implements BookService {

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> search(String description) {
        return getRepository().search(description);
    }
}
