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
                BookType type =  BookType.valueOf(resultSet.getString("type"));
                String describtion = resultSet.getString("describtion");
                LocalDate time = resultSet.getDate("time").toLocalDate();
                connection.close();
                Language language = languageRepository.getLanguageByCode(languageCode);
                return new Book(1,bookName,authorName,type, describtion, time, language);
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
                BookType type =  BookType.valueOf(resultSet.getString("type"));
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

}
