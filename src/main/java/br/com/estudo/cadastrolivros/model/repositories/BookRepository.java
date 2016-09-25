package br.com.estudo.cadastrolivros.model.repositories;

import br.com.estudo.cadastrolivros.model.domain.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends QueryDslCustomerRepository<Book, Long> {

}
