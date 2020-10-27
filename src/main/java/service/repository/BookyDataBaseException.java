package service.repository;

public class BookyDataBaseException extends Exception{

    public BookyDataBaseException(String message) {
        super(message);
    }

    public BookyDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
