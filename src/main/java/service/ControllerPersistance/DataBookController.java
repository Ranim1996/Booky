package service.ControllerPersistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;

import java.util.List;

public class DataBookController {

    final Logger logger = LoggerFactory.getLogger(DataBookController.class);
    JDBCBookRepository bookRepository = new JDBCBookRepository();

    //show all books
    public List<Book> showAllBooks(){

        try {
            List<Book> books = bookRepository.getBooks();
            for (Book book : books) {
                logger.info(book.toString());
            }
            return books;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the book with given laguage code
     * @param language of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithLanguage(Language language){

        try {
            List<Book> books = bookRepository.getBooksByLanguage(language);
            return books;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the book with given type code
     * @param type of the book to be shown.
     */
    //show book by language code
    public List<Book> bookFilteredWithType(BookType type){

        try {
            List<Book> books = bookRepository.getBooksByType(type);
            return books;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
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
            List<Book> books = bookRepository.getBooksByTypeAndLanguage(type, language);
            System.out.println(books);
            return books;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Add/create a new book.
     * @param book should be inserted into the DB.
     */
    public boolean addBook(Book book){

        try {
            bookRepository.AddBook(book);
            return true;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
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
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Show/print the book with given id
     * @param id of the book to be shown.
     */
    public Book showBookById(int id){

        try {
            Book book = bookRepository.getBookById(id);
            return book;

        } catch (BookyDatabaseException e) {
            e.printStackTrace();
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
            bookRepository.deleteBook(id);
            return true;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
