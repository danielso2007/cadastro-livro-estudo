package br.com.estudo.cadastrolivros.resources;

import br.com.estudo.cadastrolivros.interfaces.services.BookService;
import br.com.estudo.cadastrolivros.transferobject.BookTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody BookTransferObject book) {
        bookService.save(book);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookTransferObject> getListBooks() {
        return bookService.listAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody BookTransferObject book) {
        bookService.update(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{description}")
    public List<BookTransferObject> search(@PathVariable("description") String description) {
        return bookService.searchByTitleOrAuthor(description);
    }

}
