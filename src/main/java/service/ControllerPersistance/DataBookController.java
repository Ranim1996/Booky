package service.ControllerPersistance;

import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DataBookController extends JDBCBookRepository {

    JDBCBookRepository bookRepository = new JDBCBookRepository();

    //show all books
    public List<Book> showAllBooks(){

        try {
            List<Book> books = bookRepository.getBooks();
            return books;
        }
        catch (BookyDatabaseException | SQLException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Show/print the book with given laguage code
     * @param language of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithLanguage(Language language){

        try {
            return bookRepository.getBooksByLanguage(language);
        }
        catch (BookyDatabaseException | SQLException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Show/print the book with given type code
     * @param type of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithType(BookType type){

        try {
            return bookRepository.getBooksByType(type);
        }
        catch (BookyDatabaseException | SQLException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Show/print the book with given type code
     * @param type
     * @param language
     * of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithTypeAndLanguage(BookType type, Language language){

        try {
            return bookRepository.getBooksByTypeAndLanguage(type, language);
        }
        catch (BookyDatabaseException | SQLException e) {
            return Collections.emptyList();
        }
    }


    /**
     * Add/create a new book.
     * @param book should be inserted into the DB.
     */
    public boolean addBook(Book book){

        try {
            bookRepository.addBook(book);
            return true;
        }
        catch (BookyDatabaseException | SQLException e) {
            return false;
        }
    }

    /**
     * Update a new book.
     * @param id
     * @param book
     * should be updated into the DB.
     */
    public boolean updateBook(int id, Book book){

        try {
            bookRepository.updateBook(id, book);
            return true;
        }
        catch (BookyDatabaseException | SQLException e) {
            return false;
        }
    }

    /**
     * Show/print the book with given id
     * @param id of the book to be shown.
     */
    public Book showBookById(int id){

        try {
            return bookRepository.getBookById(id);

        } catch (BookyDatabaseException | SQLException e) {
            return null;
        }
    }

    /**
     * This method deletes the book record from the DB for given book id.
     * @param id of the book who should be deleted from the DB
     * @throws BookyDatabaseException
     */
    public boolean deleteBook(int id){

        try{
            System.out.println("in book controller");
            bookRepository.deleteBook(id);
            System.out.println("isdeleted");
            return true;
        }
        catch (BookyDatabaseException | SQLException e) {
            return false;
        }
    }

    /**
     * Show/print the book with the chars/letters
     * @param chars
     */
    //show books by given chars
    public List<Book> bookFilterByFirstNameChars(String chars){

        try {
            List<Book> books = bookRepository.getBooksByName(chars);
            return books;
        }
        catch (BookyDatabaseException | SQLException e) {
            return null;
        }
    }
}
