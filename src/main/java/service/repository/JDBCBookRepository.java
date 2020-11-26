package service.repository;

import service.model.Book;
import service.model.BookType;
import service.model.Language;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class JDBCBookRepository  extends JDBCRepository{

    //get all books from data base
    public List<Book> getBooks() throws BookyDatabaseException, SQLException {

        Map<String, Language> languages = this.getUsedLanguages();
        List<Book> books = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM book";

        try (Statement statement = connection.createStatement()){
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

            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read books from the database.",throwable);
        }finally {
            connection.close();
        }
        return books;
    }

    //get all books with the given language from data base
    public List<Book> getBooksByLanguage(Language language) throws BookyDatabaseException, SQLException {

        Map<String, Language> languages = this.getUsedLanguages();
        List<Book> filtered = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM book WHERE language_code = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, language.getCode()); // set language_code parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                BookType type =  BookType.valueOf(resultSet.getString("bookType"));
                String describtion = resultSet.getString("describtion");
                LocalDate time = resultSet.getDate("time").toLocalDate();

                String code = resultSet.getString("language_code");

                Language bLanguage = languages.get(code);

                Book book = new Book(id,bookName,authorName,type, describtion, time, bLanguage);
                filtered.add(book);

            }
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read books from the database.",throwable);
        }finally {
            connection.close();
        }

        return filtered;
    }

    //get all books with the given type from data base
    public List<Book> getBooksByType(BookType type) throws BookyDatabaseException, SQLException {

        Map<String, Language> languages = this.getUsedLanguages();
        List<Book> filtered = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM book WHERE bookType = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type.name()); // set type parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                BookType bType =  BookType.valueOf(resultSet.getString("bookType"));
                String describtion = resultSet.getString("describtion");
                LocalDate time = resultSet.getDate("time").toLocalDate();

                String code = resultSet.getString("language_code");

                Language bLanguage = languages.get(code);

                Book book = new Book(id,bookName,authorName,bType, describtion, time, bLanguage);
                filtered.add(book);

            }

            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read books from the database.",throwable);
        }finally {
            connection.close();
        }
        return filtered;
    }


    //get all books with the given type and language from data base
    public List<Book> getBooksByTypeAndLanguage(BookType type, Language language) throws BookyDatabaseException, SQLException {

        Map<String, Language> languages = this.getUsedLanguages();
        List<Book> filtered = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM book WHERE bookType = ? AND language_code = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, type.name()); // set type parameter
            statement.setString(2, language.getCode()); // set type parameter

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                BookType bType =  BookType.valueOf(resultSet.getString("bookType"));
                String describtion = resultSet.getString("describtion");
                LocalDate time = resultSet.getDate("time").toLocalDate();

                String code = resultSet.getString("language_code");

                Language bLanguage = languages.get(code);

                Book book = new Book(id,bookName,authorName,bType, describtion, time, bLanguage);
                filtered.add(book);

            }

            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read books from the database.",throwable);
        }finally {
            connection.close();
        }
        return filtered;
    }



    //mapping the languages
    private Map<String, Language> getUsedLanguages() throws BookyDatabaseException, SQLException {

        Map<String, Language> languages = new HashMap<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM language WHERE code in (select language_code from book)";

        try (Statement statement = connection.createStatement()){
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
        }finally {
            connection.close();
        }
        return  languages;
    }

    public void addBook(Book book) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "INSERT INTO book ( bookName, authorName, bookType, describtion, time, language_code) " +
                "VALUES (?,?,?,?,?,?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setString(3, book.getBookType().name());
            preparedStatement.setString(4, book.getDescribtion());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            preparedStatement.setString(6, book.getLanguage_code().getCode());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot create new book.", throwable);
        }finally {
            connection.close();
        }
    }

    public Book getBookById(int id) throws BookyDatabaseException, SQLException {

        JDBCLanguageRepository languageRepository = new JDBCLanguageRepository();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM book WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
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
        }finally {
            connection.close();
        }

    }

    public void updateBook(int id, Book book) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "UPDATE book SET bookName = ? ,authorName = ? ,bookType = ?," +
                "describtion = ? ,time = ? ,language_code = ? WHERE id = ?";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setString(3, book.getBookType().name());
            preparedStatement.setString(4, book.getDescribtion());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            preparedStatement.setString(6, book.getLanguage_code().getCode());
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot update book.", throwable);
        }finally {
            connection.close();
        }
    }

    public void deleteBook(int bId) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "Delete FROM book where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,bId);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException throwable){
            throw  new BookyDatabaseException("Cannot delete book.", throwable);
        }finally {
            connection.close();
        }

    }


}
