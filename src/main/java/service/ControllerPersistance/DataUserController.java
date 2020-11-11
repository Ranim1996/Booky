package service.ControllerPersistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Users;
import service.repository.BookyDatabaseException;
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
    public Users ShowUserById(int id){
        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            Users user = userRepository.GetUserById(id);
//            logger.info(user.toString());
//            System.out.println("hi");
            System.out.println("message user controller" + user);
            return user;

        } catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Update a new user.
     * @param id
     * @param user
     * should be updated into the DB.
     */
    public boolean updateUser(int id, Users user){

        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            userRepository.UpdateUser(id, user);
            return true;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return false;
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
