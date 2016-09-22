package br.com.estudo.cadastrolivros.interfaces.dao;

import br.com.estudo.cadastrolivros.modal.domain.Book;

import java.util.List;

public interface BookDAO extends GenericDAO<Book> {

    List<Book> searchBook(String description);

}
