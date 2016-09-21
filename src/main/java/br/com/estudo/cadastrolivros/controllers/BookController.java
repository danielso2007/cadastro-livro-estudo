package br.com.estudo.cadastrolivros.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.modal.domain.Book;

@Controller
@Path("/book")
public class BookController {
	
	@Autowired
	@Qualifier("bookServiceImpl")
	private BookService bookServiceImpl;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(Book book){
		bookServiceImpl.save(book);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getListBooks(){
		return bookServiceImpl.listAll();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Book book){
		bookServiceImpl.update(book);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void delete(@PathParam("id") int id){		
	Book book = bookServiceImpl.getById(id);
	bookServiceImpl.delete(book);
		
	}
	
	@Path("/search/{description}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Book> search(@PathParam("description")String description){

	return bookServiceImpl.search(description);
	}
	
	public BookService getBookServiceImpl() {
		return bookServiceImpl;
	}
	
}
