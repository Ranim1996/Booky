package service.ControllerPersistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Book;
import service.model.UserType;
import service.model.Users;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;
import service.repository.JDBCUserRepository;

import java.sql.SQLException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

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
            System.out.println("In user controller" + user);
            return user;

        } catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Show/print the user with given user type
     * @param type of the user to be shown.
     */
    public Users ShowUserByType(UserType type){

        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            Users user = userRepository.GetUsersByType(type);
            System.out.println("In user controller" + user);
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
     * Get a new user.
     * @param email should show user by email.
     */
    public Users getUserByEmail(String email) {

        JDBCUserRepository userRepository = new JDBCUserRepository();

        try{
            Users u = null;
            List<Users> users = userRepository.getUsers();
            for (Users p : users){
                if(p.getEmail().equals(email)){
                    return p;
                }
            }

        } catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * log into account
     * @param email
     * @param password
     * log in with email and password
     */
    public boolean login(String email, String password){

        Users u = getUserByEmail(email);

        if(u.equals(null)){
            return false;
        }
        if(u.getPassword().equals(password)){
            System.out.println("login is done" + u);
            return true;
        }
        return false;
    }

    /**
     * log into account
     * @param id
     * @param auth
     * log in with email and password
     */
    public boolean isIdAndAuthSame(int id, String auth) {

        String encodedCredentials = auth.replaceFirst("Basic ", "");
        String credentials = new String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

        System.out.println(credentials);

        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String email = tokenizer.nextToken();

        System.out.println(email);

        Users user = ShowUserById(id);

        if (!user.getEmail().equals(email)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Add/create a new book.
     * @param user should be inserted into the DB.
     */
    public boolean addUser(Users user){

        JDBCUserRepository userRepository = new JDBCUserRepository();


        try {
            userRepository.AddUser(user);
            return true;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
