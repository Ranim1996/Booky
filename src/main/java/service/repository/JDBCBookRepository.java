package service.repository;

import service.model.Book;
import service.model.BookType;
import service.model.Language;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class JDBCBookRepository  extends JDBCRepository{

    //get book from data base by code
    public Book getBookByLanguageCode(String languageCode) throws BookyDatabaseException {

        Connection connection = this.getDataBaseConneection();

        JDBCLanguageRepository languageRepository = new JDBCLanguageRepository();

        String sql = "SELECT * FROM book WHERE language_code = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, languageCode); // set language_code parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new BookyDatabaseException("Book with language code " + languageCode + " cannot be found");
            } else {
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                BookType type =  BookType.valueOf(resultSet.getString("bookType"));
                String describtion = resultSet.getString("describtion");
                LocalDate time = resultSet.getDate("time").toLocalDate();
                connection.close();
                Language language = languageRepository.getLanguageByCode(languageCode);
                return new Book(0,bookName,authorName,type, describtion, time, language);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read Book from the database.",throwable);
        }
    }

    //get all books from data base
    public Collection<Book> getBooks() throws BookyDatabaseException {

        Map<String, Language> languages = this.getUsedLanguages();
        List<Book> books = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT * FROM book";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                BookType type =  BookType.valueOf(resultSet.getString("bookType"));
                String describtion = resultSet.getString("describtion");
                LocalDate time = resultSet.getDate("time").toLocalDate();

                String code = resultSet.getString("language_code");

                Language language = languages.get(code);

                Book book = new Book(id,bookName,authorName,type, describtion, time, language);
                books.add(book);
            }

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read books from the database.",throwable);
        }
        return books;
    }

    private Map<String, Language> getUsedLanguages() throws BookyDatabaseException {
        Map<String, Language> languages = new HashMap<>();
        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT * FROM language WHERE code in (select language_code from book)";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                Language language = new Language(code, name);
                languages.put(code, language);
            }
            connection.commit();
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read languages from the database.",throwable);
        }
        return  languages;
    }

    public void AddBook(Book book) throws BookyDatabaseException {

        Connection connection = this.getDataBaseConneection();

        String sql = "INSERT INTO book ( bookName, authorName, bookType, describtion, time, language_code) VALUES (?,?,?,?,?,?) ";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setString(3, book.getType().name());
            preparedStatement.setString(4, book.getDescribtion());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            preparedStatement.setString(6, book.getLanguage().getCode());
            preparedStatement.executeUpdate();
            connection.commit();

            String sqlID = "SELECT max(id) ID FROM book";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlID);
            if (resultSet.next()){
                int bookId = resultSet.getInt("ID");
                connection.commit();
                connection.close();
            } else {
                throw  new BookyDatabaseException("Cannot get the id of the new book.");
            }

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot create new book.", throwable);
        }
    }

    public Book GetBookById(int id) throws BookyDatabaseException{

        JDBCLanguageRepository languageRepository = new JDBCLanguageRepository();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM book WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id); // set id parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new BookyDatabaseException("Book with id " + id + " cannot be found");
            } else {
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                BookType type =  BookType.valueOf(resultSet.getString("bookType"));
                String describtion = resultSet.getString("describtion");
                LocalDate time = resultSet.getDate("time").toLocalDate();
                String languageCode = resultSet.getString("language_code");

                connection.close();
                Language language = languageRepository.getLanguageByCode(languageCode);
                return new Book(0,bookName,authorName,type, describtion, time, language);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read books from the database.",throwable);
        }

    }

    public void UpdateBook(int id, Book book) throws BookyDatabaseException {

        Connection connection = this.getDataBaseConneection();

        String sql = "UPDATE book SET bookName = ? ,authorName = ? ,bookType = ?," +
                "describtion = ? ,time = ? ,language_code = ? WHERE id = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setString(3, book.getType().name());
            preparedStatement.setString(4, book.getDescribtion());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            preparedStatement.setString(6, book.getLanguage().getCode());
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot update book.", throwable);
        }
    }

    public void deleteBook(int bId) throws BookyDatabaseException {

        Connection connection = this.getDataBaseConneection();

        String sql = "Delete FROM book where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bId);
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch (SQLException throwable){
            throw  new BookyDatabaseException("Cannot delete book.", throwable);
        }

    }


}
