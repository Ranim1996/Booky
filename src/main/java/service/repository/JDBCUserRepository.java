package service.repository;

import service.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class JDBCUserRepository extends JDBCRepository {

    //get all users from data base
    public Collection<Users> getUsers() throws BookyDatabaseException {

        Map<String, Country> countries = this.getUsedCountries();
        Map<String, Language> languages = this.getUsedLanguages();

        List<Users> users = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                UserType type = UserType.valueOf(resultSet.getString("type"));
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phoneNumber");
                String dateOfBirth = resultSet.getString("dateOfBirth");

                String languageCode = resultSet.getString("language");
                Language language = languages.get(languageCode);
                String countryCode = resultSet.getString("country");
                Country country = countries.get(countryCode);


                Users user = new Users(id, firstName, lastName, dateOfBirth, type,email,password,phoneNumber,country,language);
                users.add(user);
            }

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read users from the database.",throwable);
        }
        return users;
    }

    //get used countries in the db
    private Map<String, Country> getUsedCountries() throws BookyDatabaseException {
        Map<String, Country> countries = new HashMap<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM country WHERE code in (select country from users)";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                Country country = new Country(code, name);
                countries.put(code, country);
            }
            connection.commit();
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read countries from the database.",throwable);
        }
        return  countries;
    }

    //get used countries in the db
    private Map<String, Language> getUsedLanguages() throws BookyDatabaseException {
        Map<String, Language> languages = new HashMap<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM language WHERE code in (select language from users)";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                Language language = new Language(code, name);
                languages.put(code, language);
            }
            connection.commit();
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read languages from the database.",throwable);
        }
        return  languages;
    }

    public Users GetUserById(int id) throws BookyDatabaseException{

        JDBCLanguageRepository languageRepository = new JDBCLanguageRepository();
        JDBCCountryRepository countryRepository = new JDBCCountryRepository();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id); // set id parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new BookyDatabaseException("User with id " + id + " cannot be found");
            } else {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                UserType type = UserType.valueOf(resultSet.getString("type"));
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phoneNumber");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String languageCode = resultSet.getString("language");
                String countryCode = resultSet.getString("country");

                connection.close();

                Language language = languageRepository.getLanguageByCode(languageCode);
                Country country = countryRepository.getCountryByCode(countryCode);

                return new Users(0,firstName, lastName, dateOfBirth, type,email,password,phoneNumber,country,language);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read users from the database.",throwable);
        }

    }

}
