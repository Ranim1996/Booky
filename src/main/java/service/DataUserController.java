package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Book;
import service.model.Users;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;
import service.repository.JDBCUserRepository;

import java.util.Collection;

public class DataUserController {

    final Logger logger = LoggerFactory.getLogger(DataUserController.class);

    /**
     * Show/print all users.
     */
    void showAllUsers(){
        JDBCUserRepository usersRepository = new JDBCUserRepository();
        try {
            Collection<Users> users = usersRepository.getUsers();
            for (Users user : users) {
                logger.info(user.toString());

            }
        } catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show/print the user with given id
     * @param id of the user to be shown.
     */
    void ShowUserById(int id){
        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            Users user = userRepository.GetUserById(id);
            logger.info(user.toString());

        } catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update a new user.
     * @param id
     * @param user
     * should be updated into the DB.
     */
    void updateUser(int id, Users user){

        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            userRepository.UpdateUser(id, user);
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add/create a new user.
     * @param user should be inserted into the DB.
     */
    void addUser(Users user){
        JDBCUserRepository usersRepository = new JDBCUserRepository();
        try {
            usersRepository.addUser(user);
        } catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
    }

}
