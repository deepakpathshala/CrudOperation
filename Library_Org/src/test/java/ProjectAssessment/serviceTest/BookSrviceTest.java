package ProjectAssessment.serviceTest;

import ProjectAssessment.entity.Book;
import ProjectAssessment.repo.BookRepository;
import ProjectAssessment.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookSrviceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("123456789");
    }

    @Test
    public void testSaveBook() {
        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = bookService.saveBook(book);
        assertNotNull(savedBook);
        assertEquals("Test Title", savedBook.getTitle());
    }

    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
    }

    @Test
    public void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> retrievedBook = bookService.getBookById(1L);
        assertTrue(retrievedBook.isPresent());
    }

    @Test
    public void testUpdateBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        Book updatedBook = bookService.updateBook(1L, book);
        assertNotNull(updatedBook);
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

}
