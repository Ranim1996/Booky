package service.repository;

import service.model.Book;
import service.model.BookType;
import service.model.Like;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCLikeRepository extends JDBCRepository {

    //get all likes
    public List<Like> getLikes() throws BookyDatabaseException {

        List<Like> likes = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT b.id, b.bookName, b.authorName, b.bookType, b.describtion, b.language_code, l.id AS likeId " +
                "FROM ((users INNER JOIN likes l ON users.id = l.userId) INNER JOIN book b ON l.bookId = b.id) " +
                "GROUP BY l.id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int Id = resultSet.getInt("id");
                int bookID = resultSet.getInt("bookId");
                int likerId = resultSet.getInt("userId");
                Like like = new Like(Id, bookID, likerId);

                likes.add(like);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read likes from the database.",throwable);
        }
        return likes;
    }

    //get likes by user id and book id
    public List<Book> getLikedBooksByUserId(int uId) throws BookyDatabaseException {

        List<Book> books = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT b.id, b.bookName, b.authorName, b.bookType, b.describtion, b.language_code, l.id AS likeId " +
                "FROM ((users INNER JOIN likes l ON users.id = l.userId) INNER JOIN book b ON l.bookId = b.id) " +
                "WHERE users.id = ? GROUP BY l.id";

        try {

            System.out.println("try JDBC");

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, uId);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                int Id = resultSet.getInt("id");
                String name = resultSet.getString("bookName");
                String author = resultSet.getString("authorName");
                BookType type =  BookType.valueOf(resultSet.getString("bookType"));

                System.out.println("after likes");

                Book book = new Book(Id, name, author,type);

                books.add(book);
            }
            connection.close();

            return books;

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read likes from the database.",throwable);
        }
    }

    //add like
   public void addLike(Like like) throws BookyDatabaseException {

       Connection connection = this.getDataBaseConneection();

        String sql = "INSERT INTO likes (bookId, userId) SELECT * FROM (SELECT ?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT userId FROM likes WHERE userId = ? AND bookId = ?) LIMIT 1";
       try {

           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1, like.getBookId());
           preparedStatement.setInt(2, like.getUserId());
           preparedStatement.setInt(3, like.getUserId());
           preparedStatement.setInt(4, like.getBookId());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

       } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot add like.", throwable);
        }
    }

    //remove like
    public boolean deleteLike(Like like) throws BookyDatabaseException{

        Connection connection = this.getDataBaseConneection();

        String sql = "DELETE FROM likes WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,like.getId());

            statement.executeUpdate();
            connection.commit();
            connection.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
