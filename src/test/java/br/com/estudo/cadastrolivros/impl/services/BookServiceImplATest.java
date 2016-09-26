package br.com.estudo.cadastrolivros.impl.services;

import br.com.estudo.cadastrolivros.enums.StatusBookEnum;
import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.model.domain.Book;
import br.com.estudo.cadastrolivros.transferobject.BookTransferObject;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @see https://springtestdbunit.github.io/spring-test-dbunit/sample.html
 */
@DatabaseSetup("classpath:data/dataset.xml")
@Transactional(transactionManager = "transactionManager")
public class BookServiceImplATest extends TestConfig {

    @Autowired
    private BookService bookService;

    private List<BookTransferObject> listBook;
    private String description;

    @Test
    public void testSaveBookWithSuccessAnyExceptionThrow() throws Exception {
        BookTransferObject bookAngularJS = getInstanceBook();
        try {
            System.out.println(bookAngularJS);
            bookService.save(bookAngularJS);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not expected result");
        }
    }

    @Test
    public void testGetBookById() throws Exception {
        Long id = 1L;
        BookTransferObject book = bookService.getById(id);
        assertNotNull(book);
    }

    @Test
    public void testSearchBookByTitle() throws Exception {
        description = "Tdd";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertFalse(listBook.isEmpty());
    }

    @Test
    public void testSearchBookByAuthorName() throws Exception {
        description = "Daniel";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertFalse(listBook.isEmpty());
    }

    @Test
    public void testSearchBookByAuthorLastName() throws Exception {
        description = "Oliveira";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertFalse(listBook.isEmpty());
    }

    @Test
    public void testSearchBookByTitleCaseSensitiveIgnored() throws Exception {
        description = "tdd";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertFalse(listBook.isEmpty());
    }

    @Test
    public void testSearchBookByAuthorNameCaseSensitiveIgnored() throws Exception {
        description = "Daniel";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertFalse(listBook.isEmpty());
    }

    @Test
    public void searchBookByAuthorNameNotFoundBookNotExists() throws Exception {
        description = "paulo coelho";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertTrue(listBook.isEmpty());
    }

    @Test
    public void testSearchBookByAuthorNameAndLastName() throws Exception {
        description = "Daniel Oliveira";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertFalse(listBook.isEmpty());
    }

    @Test
    public void testSearchBookInDraftStatusCannotBeFound() throws Exception {
        description = "Angular";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertTrue(listBook.isEmpty());
    }

    @Test
    public void testSearchBookByTitleNotExistBookWasNotFound() throws Exception {
        description = "NodeJS";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertTrue(listBook.isEmpty());
    }

    @Test
    public void testMoreOneBookFoundByTheSameTitleWord() throws Exception {
        description = "Ruby";
        listBook = bookService.searchByTitleOrAuthor(description);
        assertFalse(listBook.isEmpty());
        int totalExpectedBook = 2;
        assertEquals(totalExpectedBook, listBook.size());
    }


    private BookTransferObject getInstanceBook() throws Exception {
        BookTransferObject bookAngularJS = new BookTransferObject();
        bookAngularJS.setTitle("AngularJS na Prática");
        bookAngularJS.setAuthor("Daniel");
        bookAngularJS.setCoverUrl("http://www.bookshandson.com.br/bookcover.png");
        bookAngularJS.setStatus(StatusBookEnum.PUBLISHED);
        bookAngularJS.setTotalPage(150L);
        bookAngularJS.setYearPublished("2014");
        return bookAngularJS;
    }

}
