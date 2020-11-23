package service.ControllerPersistance;

import service.model.Like;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLikeRepository;

import java.util.List;

public class DataLikeController {

    //show all likes from the db
    public List<Like> getLikes(){

        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            List<Like> likes = (List<Like>) likeRepository.getLikes();

            return likes;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Show/print the likes by the given book id
     * @param id .
     */
    //get likes by book id
    public List<Like> getLikesByBook(int id){

        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            List<Like> likes = (List<Like>) likeRepository.getLikesByBook(id);

            return likes;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Show/print the likes by the given book id and user id
     * @param id .
     */
    //get likes by book id and user id
    public Like getLikesByBookAndUser(int id,int userId){
        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            Like like = (Like) likeRepository.getLikeByBookAndUser(id,userId);
            return like;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * add like to db
     * @param like .
     */
    //add like
    public boolean likeBook(Like like) {

        System.out.println("hi repository");


        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            System.out.println("hi try repository");
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
}
