package Test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ControllerPersistance.DataBookController;
import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookDataTest {

    @InjectMocks
    DataBookController bookController;

    @Mock
    JDBCBookRepository bookRepository;

    @Test // test the size of the array
    void ShowAllBooks() throws BookyDatabaseException, SQLException {

        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");
        List<Book> books = new ArrayList<>();

        Book book1 = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");
        Book book2 = new Book(2,"book2","author2",BookType.Classics,"info",date,language,"");

        books.add(book1);
        books.add(book2);

        when(bookRepository.getBooks()).thenReturn(books);

        List<Book> bookList = bookController.showAllBooks();

        assertEquals(books.size(), bookList.size());
    }

    @Test
    void GetBooksByType() throws BookyDatabaseException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");
        List<Book> books = new ArrayList<>();

        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");
        books.add(book);

        when(bookRepository.getBooksByType(BookType.LitraryFiction)).thenReturn(books);

        List<Book> bookList = bookController.bookFilteredWithType(BookType.LitraryFiction);

        assertEquals(books.size(), bookList.size());
    }

    @Test
    void GetBooksByLanguage() throws BookyDatabaseException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");
        List<Book> books = new ArrayList<>();

        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");
        books.add(book);

        when(bookRepository.getBooksByLanguage(language)).thenReturn(books);

        List<Book> bookList = bookController.bookFilteredWithLanguage(language);

        assertEquals(books.size(), bookList.size());
    }

    @Test
    void GetBooksByTypeAndLanguage() throws BookyDatabaseException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");
        List<Book> books = new ArrayList<>();

        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");
        books.add(book);

        when(bookRepository.getBooksByTypeAndLanguage(BookType.LitraryFiction,language)).thenReturn(books);

        List<Book> bookList = bookController.bookFilteredWithTypeAndLanguage(BookType.LitraryFiction,language);

        assertEquals(books.size(), bookList.size());
    }

    @Test
    public void addBook() throws BookyDatabaseException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");

        when(bookRepository.addBook(book)).thenReturn(true);

        boolean addedBook = bookController.addBook(book);

        Assert.assertEquals(true, addedBook);
    }

    @Test
    public void updateBook() throws BookyDatabaseException, SQLException{
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");

        when(bookRepository.updateBook(1,book)).thenReturn(true);

        boolean updatedBook = bookController.updateBook(1,book);

        Assert.assertEquals(true, updatedBook);
    }

    @Test
    public void deleteBook() throws BookyDatabaseException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");

        when(bookRepository.deleteBook(1)).thenReturn(true);

        boolean deletedBook = bookController.deleteBook(1);

        Assert.assertEquals(true, deletedBook);
    }

    @Test
    void GetBooksByName() throws BookyDatabaseException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");
        List<Book> books = new ArrayList<>();

        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");
        books.add(book);

        when(bookRepository.getBooksByName("oo")).thenReturn(books);

        List<Book> bookList = bookController.bookFilterByFirstNameChars("oo");

        assertEquals(books.size(), bookList.size());
    }

}
