package service.ControllerPersistance;

import service.model.Book;
import service.model.Like;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLikeRepository;

import java.util.List;

public class DataLikeController {

    /**
     * add like to db
     * @param like .
     */
    //add like
    public boolean likeBook(Like like) {

        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            likeRepository.addLike(like);
            return true;

        } catch (BookyDatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * delete like from db
     * @param like .
     */
    //delete like from db
    public boolean deleteLike(Like like){

        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            return likeRepository.deleteLike(like);
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Show/print the books based on user id
     * @param userId
     */
    //show books added to my favorite list
    public List<Book> LikedBooksByUser(int userId){

        System.out.println("Controller");

        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            List<Book> likedBooks = likeRepository.getLikedBooksByUserId(userId);
            System.out.println("My List: " + likedBooks);
            return likedBooks;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the liked books
     */
    public List<Like> GetAllLikes(){

        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            List<Like> likes = likeRepository.getLikes();
            System.out.println(likes);
            return likes;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
