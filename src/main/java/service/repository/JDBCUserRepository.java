package service.repository;

import service.model.*;

import java.sql.*;
import java.util.*;

public class JDBCUserRepository extends JDBCRepository {

    //get all users from data base
    public List<Users> getUsers() throws BookyDatabaseException, SQLException {

        List<Users> users = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                UserType type = UserType.valueOf(resultSet.getString("userType"));
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String dateOfBirth = resultSet.getString("dateOfBirth");

                Users user = new Users(id, firstName, lastName, dateOfBirth, type,email,password);
                users.add(user);
            }
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read users from the database.",throwable);
        }finally {
            connection.close();
        }
        return users;
    }

    //get user by id
    public Users getUserById(int id) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id); // set id parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new BookyDatabaseException("User with id " + id + " cannot be found");
            } else {
                int UId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                UserType type = UserType.valueOf(resultSet.getString("userType"));
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String dateOfBirth = resultSet.getString("dateOfBirth");

                connection.close();

                return new Users(UId,firstName, lastName, dateOfBirth, type,email,password);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read users from the database.",throwable);
        }finally {
            connection.close();
        }
    }

    // update user data
    public boolean updateUser(int id, Users user) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "UPDATE users SET firstName = ? ,lastName = ? , userType = ?," +
                "email = ? ,password = ?, dateOfBirth = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsertype().name());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getDateOfBirth());


            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

            return true;

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot update user information.", throwable);
        }finally {
            connection.close();
        }
    }

    //get user by type
    public Users getUsersByType(UserType userType) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM users WHERE userType = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, userType.name()); // set usertype parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new BookyDatabaseException("User with type " + userType + " cannot be found");
            } else {
                int UId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                UserType type = UserType.valueOf(resultSet.getString("userType"));
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String dateOfBirth = resultSet.getString("dateOfBirth");

                connection.close();

                return new Users(UId,firstName, lastName, dateOfBirth, type,email,password);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read users from the database.",throwable);
        }finally {
            connection.close();
        }
    }

    public boolean addUser(Users user) throws BookyDatabaseException, SQLException {

        String type = UserType.Reader.name();

        MD5Hash md = new MD5Hash();

        Connection connection = this.getDataBaseConneection();

        String sql = "INSERT INTO users ( firstName, lastName, userType, email, password, dateOfBirth) VALUES (?,?,?,?,?,?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, md.oneWayHashing(user.getPassword()));
            preparedStatement.setString(6, user.getDateOfBirth());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

            return true;

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot create new user.", throwable);
        }finally {
            connection.close();
        }

    }

}
