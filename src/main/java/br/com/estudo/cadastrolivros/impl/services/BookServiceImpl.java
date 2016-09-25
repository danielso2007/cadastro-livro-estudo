package br.com.estudo.cadastrolivros.impl.services;

import br.com.estudo.cadastrolivros.enums.StatusBookEnum;
import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.model.domain.Book;
import br.com.estudo.cadastrolivros.model.domain.QBook;
import br.com.estudo.cadastrolivros.model.repositories.BookRepository;
import br.com.estudo.cadastrolivros.utils.Constants;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl extends GenericServiceImpl<Book, Long, BookRepository> implements BookService {

    private QBook qBook = QBook.book;
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchByTitleOrAuthor(String description) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qBook.status.eq(StatusBookEnum.PUBLISHED));
        predicate
          .and(qBook.title.lower().like(String.format("%s%s%s", Constants.PERCENT, description.toLowerCase(), Constants.PERCENT))
              .or(qBook.author.lower().like(String.format("%s%s%s", Constants.PERCENT, description.toLowerCase(), Constants.PERCENT)))
        );
        return super.search(predicate);
    }
}
