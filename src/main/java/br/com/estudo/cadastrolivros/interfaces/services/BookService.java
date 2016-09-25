package br.com.estudo.cadastrolivros.interfaces.services;

import java.util.List;

import br.com.estudo.cadastrolivros.model.domain.Book;
import br.com.estudo.cadastrolivros.model.repositories.BookRepository;

public interface BookService extends GenericService<Book, Long, BookRepository> {

    List<Book> search(String description);

}
