package br.com.estudo.cadastrolivros.model.repositories;


import br.com.estudo.cadastrolivros.model.domain.BaseEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface QueryDslCustomerRepository<E extends BaseEntity, ID extends Serializable> extends CrudRepository<E, ID>, QueryDslPredicateExecutor<E> {

    List<E> findAll(Predicate predicate);

}
