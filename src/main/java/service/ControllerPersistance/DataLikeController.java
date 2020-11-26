package service.ControllerPersistance;

import service.model.Book;
import service.model.Like;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;
import service.repository.JDBCLikeRepository;

import java.util.List;

public class DataLikeController {

    JDBCLikeRepository likeRepository = new JDBCLikeRepository();

    /**
     * add like to db
     * @param like .
     */
    //add like
    public boolean likeBook(Like like) {

        try {
            likeRepository.addLike(like);
            return true;

        } catch (BookyDatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Show/print the books based on user id
     * @param userId
     */
    //show books added to my favorite list
    public List<Book> LikedBooksByUser(int userId){

        try {
            List<Book> likedBooks = likeRepository.getLikedBooksByUserId(userId);
            return likedBooks;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method deletes the book record from the DB for given book id.
     * @param id
     * @param userId of the book who should be deleted from the DB
     * @throws BookyDatabaseException
     */
    public boolean DeleteBook(int id, int userId){

        try{
            likeRepository.deleteBook(id, userId);
            return true;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
