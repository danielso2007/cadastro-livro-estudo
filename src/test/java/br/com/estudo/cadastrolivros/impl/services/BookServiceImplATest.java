package br.com.estudo.cadastrolivros.impl.services;
import java.util.List;

import br.com.estudo.cadastrolivros.enums.StatusBookEnum;
import br.com.estudo.cadastrolivros.modal.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.estudo.cadastrolivros.dbunit.config.DBUnitConfiguration;
import br.com.estudo.cadastrolivros.interfaces.services.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplATest extends DBUnitConfiguration {
	
	@Autowired
	@Qualifier("bookServiceImpl")
	private BookService bookServiceImpl;
	
	private List<Book> listBook;
	private String description;
	
	@Before
	public void setUp() throws Exception {
		getSetUpOperation().execute(getConnection(), getDataSet());
	}

	@Test
	public void testSaveBookWithSuccessAnyExceptionThrow() {
		Book bookAngularJS = getInstanceBook();
		try{
			bookServiceImpl.save(bookAngularJS);
		}catch(Exception e){
			e.printStackTrace();
			fail("Not expected result");
		}
	}
	
	@Test
	public void testGetBookById(){
		int id=1;
		Book book = bookServiceImpl.getById(id);
		assertNotNull(book);
	}
	
	@Test
	public void testSearchBookByTitle(){
		description  = "Tdd";
		listBook = bookServiceImpl.search(description);
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testSearchBookByAuthorName(){
		description = "Daniel";
		listBook =bookServiceImpl.search(description);
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testSearchBookByAuthorLastName(){
		description = "Oliveira";
		listBook = bookServiceImpl.search(description);
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testSearchBookByTitleCaseSensitiveIgnored(){
		description = "tdd";
		listBook = bookServiceImpl.search(description);
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testSearchBookByAuthorNameCaseSensitiveIgnored(){
		description = "Daniel";
		listBook = bookServiceImpl.search(description);
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void searchBookByAuthorNameNotFoundBookNotExists(){
		description = "paulo coelho";
		listBook = bookServiceImpl.search(description);
		assertTrue(listBook.isEmpty());
	}
	
	@Test
	public void testSearchBookByAuthorNameAndLastName(){
		description = "Daniel Oliveira";
		listBook = bookServiceImpl.search(description);
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testSearchBookInDraftStatusCannotBeFound(){
		description = "Angular";
		listBook = bookServiceImpl.search(description);
		assertTrue(listBook.isEmpty());
	}
	
	@Test
	public void testSearchBookByTitleNotExistBookWasNotFound(){
		description = "NodeJS";
		listBook = bookServiceImpl.search(description);
		assertTrue(listBook.isEmpty());
	}
	
	@Test
	public void testMoreOneBookFoundByTheSameTitleWord(){
		description = "Ruby";
		listBook = bookServiceImpl.search(description);
		assertFalse(listBook.isEmpty());
		int totalExpectedBook = 2;
		assertEquals(totalExpectedBook, listBook.size());
	}
	

	private Book getInstanceBook() {
		Book bookAngularJS = new Book();
		bookAngularJS.setTitle("AngularJS na Prática");
		bookAngularJS.setAuthor("Daniel Oliveira");
		bookAngularJS.setCoverUrl("http://www.bookshandson.com.br/bookcover.png");
		bookAngularJS.setStatus(StatusBookEnum.PUBLISHED);
		bookAngularJS.setTotalPage(150);
		bookAngularJS.setYearPublished("2014");
		return bookAngularJS;
	}

	public BookService getBookServiceImpl() {
		return bookServiceImpl;
	}

}
