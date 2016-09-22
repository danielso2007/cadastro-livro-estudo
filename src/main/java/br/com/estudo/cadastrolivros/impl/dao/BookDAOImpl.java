package br.com.estudo.cadastrolivros.impl.dao;

import br.com.estudo.cadastrolivros.enums.StatusBookEnum;
import br.com.estudo.cadastrolivros.interfaces.dao.BookDAO;
import br.com.estudo.cadastrolivros.modal.domain.Book;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDAOImpl extends GenericHibernateDAO<Book> implements BookDAO {

    public static final String STATUS = "status";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";

    public BookDAOImpl() {
        super();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Book> searchBook(String description) {
        Criteria criteria = getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq(STATUS, StatusBookEnum.PUBLISHED));
        Criterion title = Restrictions.ilike(TITLE, description, MatchMode.ANYWHERE);
        Criterion author = Restrictions.ilike(AUTHOR, description, MatchMode.ANYWHERE);
        LogicalExpression orExpression = Restrictions.or(title, author);
        criteria.add(orExpression);

        return criteria.list();
    }


}
