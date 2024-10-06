package ProjectAssessment.controller;

import ProjectAssessment.entity.Book;
import ProjectAssessment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookById = bookService.getBookById(id);
        return new ResponseEntity<>(bookById.get(),HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
