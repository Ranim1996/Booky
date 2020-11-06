package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Book;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;

import java.util.Collection;
import java.util.List;

public class DataBookController {

    final Logger logger = LoggerFactory.getLogger(DataBookController.class);

    //show all books
    public List<Book> showAllBooks(){

        JDBCBookRepository bookRepository = new JDBCBookRepository();

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
     * Show/print the book with given code
     * @param code of the book to be shown.
     */
    //show book by language code
    void showBookByLanguageCode(String code){

        JDBCBookRepository bookRepository = new JDBCBookRepository();

        try {
            Book book = bookRepository.getBookByLanguageCode(code);
            logger.info(book.toString());
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add/create a new book.
     * @param book should be inserted into the DB.
     */
    public boolean addBook(Book book){

        JDBCBookRepository bookRepository = new JDBCBookRepository();

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

        JDBCBookRepository bookRepository = new JDBCBookRepository();

        try {
            bookRepository.UpdateBook(id, book);
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
    public Book ShowBookById(int id){
        JDBCBookRepository bookRepository = new JDBCBookRepository();

        try {
            Book book = bookRepository.GetBookById(id);
//            logger.info(book.toString());
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
    public boolean DeleteBook(int id){

        JDBCBookRepository bookRepository = new JDBCBookRepository();

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
