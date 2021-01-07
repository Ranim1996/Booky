package service.ControllerPersistance;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;import service.model.UserType;
import service.model.Users;
import service.repository.BookyDatabaseException;
import service.repository.JDBCUserRepository;
import service.repository.MD5Hash;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.SQLException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class DataUserController extends JDBCUserRepository {


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

    public Users getUser(int userId){

        JDBCUserRepository userRepository = new JDBCUserRepository();
        try {
            Users user = userRepository.getUserById(userId);

            System.out.println("ok");

            return user;
        } catch (SQLException | BookyDatabaseException e) {
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

        System.out.println("User: " + u);

        System.out.println("given password: " + password);
        String encryptedPassword = md.oneWayHashing(password);

        System.out.println("encrypted password: " + encryptedPassword );

        if(u.getPassword().equals(encryptedPassword)){
            return true;
        }
        return false;
    }

    public Users getUserFromToken(String token) {
        Claims decoded = decodeJWT(token);

        String id = decoded.getId();

        Users u = getUser(parseInt(id));

        return u;
    }

    public static String SECRET_KEY = "oeRaYY";

    //Sample method to construct a JWT
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public  Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * Add/create a new user.
     * @param user should be inserted into the DB.
     */
    public boolean addUser(Users user) {

        JDBCUserRepository userRepository = new JDBCUserRepository();

        try {
            if(userRepository.addUser(user)) {

                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException | BookyDatabaseException e) {
            return false;
        }
    }

}
