package Test.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;
import service.repository.JDBCRepository;

import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

    @InjectMocks
    JDBCBookRepository bookRepository;

    @Mock
    JDBCRepository jdbcRepository;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName ("org.h2.Driver");

        lenient().when(jdbcRepository.getDataBaseConneection()).thenReturn(
                DriverManager.getConnection("jdbc:h2:mem:~/test"));

        RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:data.sql", UTF8, false);
    }

    @Test
    void getBooks() throws BookyDatabaseException, URISyntaxException, SQLException {
        List<Book> books = bookRepository.getBooks();
        assertEquals(0,books.size());
    }

    @Test
    void getBooksByLanguage() throws BookyDatabaseException, URISyntaxException, SQLException {
        Language language = new Language("AR", "Arabic");
        List<Book> books = bookRepository.getBooksByLanguage(language);
        assertEquals(0,books.size());
    }

    @Test
    void getBooksByType() throws BookyDatabaseException, URISyntaxException, SQLException {
        List<Book> books = bookRepository.getBooksByType(BookType.Romantic);
        assertEquals(0,books.size());
    }

    @Test
    void getBooksByLanguageAndType() throws BookyDatabaseException, URISyntaxException, SQLException {
        Language language = new Language("AR", "Arabic");
        List<Book> books = bookRepository.getBooksByTypeAndLanguage(BookType.Romantic, language);
        assertEquals(0,books.size());
    }

    @Test
    void addBook() throws BookyDatabaseException, URISyntaxException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Book book = new Book(1, "Bookname", "Author Name", BookType.Romantic, "INFO", date, language, "imgage" );

        Boolean addBook = bookRepository.addBook(book);

        assertEquals(addBook,true);

    }

    @Test
    void getBookById() throws BookyDatabaseException, URISyntaxException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Book book1 = new Book(0, "Bookname", "Author Name", BookType.Romantic, "INFO", date, language, "imgage" );

        Book book = bookRepository.getBookById(1);

        assertEquals(book1,book);

    }

    @Test
    void updateBook() throws BookyDatabaseException, URISyntaxException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Book book = new Book(0, "Bookname", "Author Name", BookType.Romantic, "INFO", date, language, "imgage" );

        Boolean updatedBook = bookRepository.updateBook(1,book);

        assertEquals(updatedBook,true);
    }

//    @Test
//    void deleteBook() throws URISyntaxException, BookyDatabaseException, SQLException {
//        LocalDate date = LocalDate.now();
//        Language language = new Language("Fr","French");
//
//        Book book = new Book(1, "Bookname", "Author Name", BookType.Romantic, "INFO", date, language, "imgage" );
//
//        Boolean deleteBook = bookRepository.deleteBook(1);
//
//        assertEquals(true,true);
//
//    }

    @Test
    void getBooksByName() throws BookyDatabaseException, URISyntaxException, SQLException {
        List<Book> books = bookRepository.getBooksByName("oo");
        assertEquals(0,books.size());
    }
}
