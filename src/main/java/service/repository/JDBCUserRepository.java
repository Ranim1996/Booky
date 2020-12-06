package service.repository;

import service.model.*;

import java.sql.*;
import java.util.*;

public class JDBCUserRepository extends JDBCRepository {

//    JDBCLanguageRepository languageRepository = new JDBCLanguageRepository();
//    JDBCCountryRepository countryRepository = new JDBCCountryRepository();

    //get all users from data base
    public List<Users> getUsers() throws BookyDatabaseException, SQLException {

//        Map<String, Country> countries = this.getUsedCountries();
//        Map<String, Language> languages = this.getUsedLanguages();

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
//                String phoneNumber = resultSet.getString("phoneNumber");
                String dateOfBirth = resultSet.getString("dateOfBirth");

//                String languageCode = resultSet.getString("language_code");
//                Language language = languages.get(languageCode);
//                String countryCode = resultSet.getString("country_code");
//                Country country = countries.get(countryCode);


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

    //get used countries in the db
//    private Map<String, Country> getUsedCountries() throws BookyDatabaseException, SQLException {
//        Map<String, Country> countries = new HashMap<>();
//
//        Connection connection = this.getDataBaseConneection();
//
//        String sql = "SELECT * FROM country WHERE code in (select country_code from users)";
//
//        try (Statement statement = connection.createStatement()){
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String code = resultSet.getString("code");
//                Country country = new Country(code, name);
//                countries.put(code, country);
//            }
//            connection.commit();
//            connection.close();
//
//        } catch (SQLException throwable) {
//            throw new BookyDatabaseException("Cannot read countries from the database.",throwable);
//        }finally {
//            connection.close();
//        }
//        return  countries;
//    }
//
//    //get used languages in the db
//    private Map<String, Language> getUsedLanguages() throws BookyDatabaseException, SQLException {
//
//        Map<String, Language> languages = new HashMap<>();
//
//        Connection connection = this.getDataBaseConneection();
//
//        String sql = "SELECT * FROM language WHERE code in (select language_code from users)";
//
//        try (Statement statement = connection.createStatement()){
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String code = resultSet.getString("code");
//                Language language = new Language(code, name);
//                languages.put(code, language);
//            }
//            connection.commit();
//            connection.close();
//
//        } catch (SQLException throwable) {
//            throw new BookyDatabaseException("Cannot read languages from the database.",throwable);
//        }finally {
//            connection.close();
//        }
//        return  languages;
//    }

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
//                String phoneNumber = resultSet.getString("phoneNumber");
                String dateOfBirth = resultSet.getString("dateOfBirth");
//                String languageCode = resultSet.getString("language_code");
//                String countryCode = resultSet.getString("country_code");

                connection.close();

//                Language language = languageRepository.getLanguageByCode(languageCode);
//                Country country = countryRepository.getCountryByCode(countryCode);

                return new Users(UId,firstName, lastName, dateOfBirth, type,email,password);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read users from the database.",throwable);
        }finally {
            connection.close();
        }
    }

    // update user data
    public void updateUser(int id, Users user) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "UPDATE users SET firstName = ? ,lastName = ? , userType = ?," +
                "email = ? ,password = ?, dateOfBirth = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsertype().name());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
//            preparedStatement.setString(6, user.getPhoneNumber());
//            preparedStatement.setString(7, user.getCountry_code().getCode());
//            preparedStatement.setString(8, user.getLanguage_code().getCode());
            preparedStatement.setString(6, user.getDateOfBirth());


            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

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
//                String phoneNumber = resultSet.getString("phoneNumber");
                String dateOfBirth = resultSet.getString("dateOfBirth");
//                String languageCode = resultSet.getString("language_code");
//                String countryCode = resultSet.getString("country_code");

                connection.close();

//                Language language = languageRepository.getLanguageByCode(languageCode);
//                Country country = countryRepository.getCountryByCode(countryCode);

                return new Users(UId,firstName, lastName, dateOfBirth, type,email,password);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read users from the database.",throwable);
        }finally {
            connection.close();
        }
    }

    public void addUser(Users user) throws BookyDatabaseException, SQLException {

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
//            preparedStatement.setString(6, user.getPhoneNumber());
//            preparedStatement.setString(7, user.getCountry_code().getCode());
//            preparedStatement.setString(8, user.getLanguage_code().getCode());
            preparedStatement.setString(6, user.getDateOfBirth());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot create new user.", throwable);
        }finally {
            connection.close();
        }
    }

}
