package br.com.estudo.cadastrolivros.interfaces.dao;

import java.util.List;

import br.com.estudo.cadastrolivros.modal.domain.Book;

public interface BookDAO extends GenericDAO<Book> {

	Book getById(int id);

	List<Book> searchBook(String description);

}
