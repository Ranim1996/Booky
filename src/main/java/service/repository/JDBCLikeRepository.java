package service.repository;

import service.model.Like;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCLikeRepository extends JDBCRepository {

    //get likes from data base
    public Collection<Like> getLikes() throws BookyDatabaseException {

        List<Like> likes = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM likes";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int bookId = resultSet.getInt("bookId");
                int userId = resultSet.getInt("userId");


                Like like = new Like(Id,bookId,userId);
                likes.add(like);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read likes from the database.",throwable);
        }
        return likes;
    }

    //get likes by book id
    public Collection<Like> getLikesByBook(int id) throws BookyDatabaseException {

        List<Like> likes = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM likes WHERE bookId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int Id = resultSet.getInt("id");
                int bookId = resultSet.getInt("bookId");
                int userId = resultSet.getInt("userId");


                Like like = new Like(Id,bookId,userId);
                likes.add(like);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read likes from the database.",throwable);
        }
        return likes;
    }

    //get likes by user id and book id
    public Like getLikeByBookAndUser(int bookId,int userId) throws BookyDatabaseException {

        Like like = null;

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM likes WHERE bookId = ? AND userId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int Id = resultSet.getInt("id");
                int bookID = resultSet.getInt("bookId");
                int likerId = resultSet.getInt("userId");


                like = new Like(Id,bookID,likerId);

            }
            connection.setAutoCommit(false);
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read likes from the database.",throwable);
        }
        return like;
    }

    //add like
   public void addLike(Like like) throws BookyDatabaseException {

       System.out.println("hi jdbc");


       Connection connection = this.getDataBaseConneection();

        String sql = "INSERT INTO likes (bookId, userId) SELECT * FROM (SELECT ?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT userId FROM likes WHERE userId = ? AND bookId = ?) LIMIT 1";
       System.out.println("hi after sql");

       try {
            System.out.println("hi try jdbc");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, like.getBookId());
           System.out.println("1");

           preparedStatement.setInt(2, like.getUserId());
           System.out.println("2");

           preparedStatement.setInt(3, like.getUserId());
           System.out.println("3");

           preparedStatement.setInt(4, like.getBookId());
           System.out.println("4");


           System.out.println("hi befor prepare");

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
           System.out.println("hi after prepare");


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

//            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setInt(1,1);
            statement.executeUpdate();
//            connection.setAutoCommit(false);
            connection.commit();
            connection.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
