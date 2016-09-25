package br.com.estudo.cadastrolivros.resources;

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

import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.model.domain.Book;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Path("/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Book book) {
        bookService.save(book);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getListBooks() {
        return bookService.listAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Book book) {
        bookService.update(book);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        Book book = bookService.getById(id);
        bookService.delete(book);

    }

    @Path("/search/{description}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Book> search(@PathParam("description") String description) {
        return bookService.searchByTitleOrAuthor(description);
    }

}
