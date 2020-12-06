package service.ControllerPersistance;

import service.model.UserType;
import service.model.Users;
import service.repository.BookyDatabaseException;
import service.repository.JDBCUserRepository;
import service.repository.MD5Hash;

import java.sql.SQLException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class DataUserController {


    /**
     * Show/print all users.
     */
    void showAllUsers(){
        JDBCUserRepository usersRepository = new JDBCUserRepository();
        try {
            Collection<Users> users = usersRepository.getUsers();
        } catch (BookyDatabaseException | SQLException e) {
        }
    }

    /**
     * Show/print the user with given id
     * @param id of the user to be shown.
     */
    public Users showUserById(int id){
        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            return userRepository.getUserById(id);

        } catch (BookyDatabaseException | SQLException e) {
            return null;
        }

    }

    /**
     * Show/print the user with given user type
     * @param type of the user to be shown.
     */
    public Users showUserByType(UserType type){

        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            return userRepository.getUsersByType(type);

        } catch (BookyDatabaseException | SQLException e) {
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
            userRepository.updateUser(id, user);
            return true;
        }
        catch (BookyDatabaseException | SQLException e) {
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
            List<Users> users = userRepository.getUsers();
            for (Users p : users){
                if(p.getEmail().equals(email)){
                    return p;
                }
            }

        } catch (BookyDatabaseException | SQLException e) {
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

        MD5Hash md = new MD5Hash();
        Users u = getUserByEmail(email);

        if(u == null){
            return false;
        }
        if(u.getPassword().equals(md.oneWayHashing(password))){
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

        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String email = tokenizer.nextToken();

        Users user = showUserById(id);

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
            userRepository.addUser(user);
            return true;
        }
        catch (BookyDatabaseException | SQLException e) {
            return false;
        }
    }

}
