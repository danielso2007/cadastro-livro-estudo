package br.com.estudo.cadastrolivros.model.repositories;

import br.com.estudo.cadastrolivros.model.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.status = 'PUBLISHED' AND (lower(b.author) LIKE lower('%' || :description || '%') OR lower(b.title) LIKE lower('%' || :description || '%')) ORDER BY b.title")
    List<Book> search(@Param("description") String description);

}
