package br.com.estudo.cadastrolivros.interfaces.services;

import java.util.List;

import br.com.estudo.cadastrolivros.modal.domain.Book;

public interface BookService extends GenericService<Book> {

    Book getById(Long id);

    List<Book> search(String description);

}
