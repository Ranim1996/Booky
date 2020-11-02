package service.repository;

import service.model.Book;
import service.model.BookType;
import service.model.Language;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class JDBCBookRepository  extends JDBCRepository{

//    public Collection<Book> getBooks() throws BookyDatabaseException {
//        Map<String, Language> languages = this.getUsedCountries();
//        List<Book> books = new ArrayList<>();
//
//        Connection connection = this.getDatabaseConnection();
//
//        String sql = "SELECT * from books";
//        try {
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                int bookId = resultSet.getInt("id");
//                String bookName = resultSet.getString("book_name");
//                String authorName = resultSet.getString("author_name");
////                BookType type = resultSet.getString(BookType.valueOf("Romantic"));
//                String description = resultSet.getString("description");
//                Date time = resultSet.getDate("time");
//                String languageCode = resultSet.getString("language_code");
//                Language language = languages.get(LanguageCode);
//                Book book = new Book(bookId,bookName, languageCode);
//                students.add(student);
//            }
//            connection.commit();
//            connection.close();
//
//        } catch (SQLException throwable) {
//            throw new SchoolDatabaseException("Cannot read students from the database.",throwable);
//        }
//        return students;
//    }


}
