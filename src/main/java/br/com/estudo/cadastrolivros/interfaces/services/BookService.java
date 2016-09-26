package br.com.estudo.cadastrolivros.interfaces.services;

import java.util.List;

import br.com.estudo.cadastrolivros.model.domain.Book;
import br.com.estudo.cadastrolivros.model.repositories.BookRepository;
import br.com.estudo.cadastrolivros.transferobject.BookTransferObject;

public interface BookService extends GenericService<BookTransferObject, Book, Long, BookRepository> {

    List<BookTransferObject> searchByTitleOrAuthor(String description);

}
