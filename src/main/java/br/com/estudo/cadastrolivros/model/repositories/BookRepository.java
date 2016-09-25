package br.com.estudo.cadastrolivros.model.repositories;

import br.com.estudo.cadastrolivros.model.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> search(String description);

}
