package service.repository;

public class BookyDatabaseException extends Exception{

    public BookyDatabaseException(String message) {
        super(message);
    }

    public BookyDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
