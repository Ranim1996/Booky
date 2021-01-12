package service.ControllerPersistance;

import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DataBookController extends JDBCBookRepository {

    JDBCBookRepository bookRepository = new JDBCBookRepository();

    //show all books
    public List<Book> showAllBooks() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<Book> books = bookRepository.getBooks();
        return books;
    }

    /**
     * Show/print the book with given laguage code
     * @param language of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithLanguage(Language language) throws BookyDatabaseException, SQLException, URISyntaxException {

        return bookRepository.getBooksByLanguage(language);
    }

    /**
     * Show/print the book with given type code
     * @param type of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithType(BookType type) throws BookyDatabaseException, SQLException, URISyntaxException {

        return bookRepository.getBooksByType(type);
    }

    /**
     * Show/print the book with given type code
     * @param type
     * @param language
     * of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithTypeAndLanguage(BookType type, Language language) throws BookyDatabaseException, SQLException, URISyntaxException {

        return bookRepository.getBooksByTypeAndLanguage(type, language);

    }


    /**
     * Add/create a new book.
     * @param book should be inserted into the DB.
     */
    public boolean addBook(Book book) throws BookyDatabaseException, SQLException, URISyntaxException {

        bookRepository.addBook(book);
        return true;
    }

    /**
     * Update a new book.
     * @param id
     * @param book
     * should be updated into the DB.
     */
    public boolean updateBook(int id, Book book) throws BookyDatabaseException, SQLException, URISyntaxException {

        bookRepository.updateBook(id, book);
        return true;
    }

    /**
     * Show/print the book with given id
     * @param id of the book to be shown.
     */
    public Book showBookById(int id) throws BookyDatabaseException, SQLException, URISyntaxException {

        return bookRepository.getBookById(id);

    }

    /**
     * This method deletes the book record from the DB for given book id.
     * @param id of the book who should be deleted from the DB
     * @throws BookyDatabaseException
     */
    public boolean deleteBook(int id) throws BookyDatabaseException, SQLException, URISyntaxException {

        System.out.println("in book controller");
        bookRepository.deleteBook(id);
        System.out.println("isdeleted");
        return true;

    }

    /**
     * Show/print the book with the chars/letters
     * @param chars
     */
    //show books by given chars
    public List<Book> bookFilterByFirstNameChars(String chars) throws BookyDatabaseException, SQLException, URISyntaxException {

        List<Book> books = bookRepository.getBooksByName(chars);
        return books;
    }
}
